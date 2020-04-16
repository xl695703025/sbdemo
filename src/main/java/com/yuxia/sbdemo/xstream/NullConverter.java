package com.yuxia.sbdemo.xstream;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.core.ReferencingMarshallingContext;
import com.thoughtworks.xstream.core.util.ArrayIterator;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

import java.lang.reflect.Field;
import java.util.*;
/**
 * 在xstream中注册这个converter，可以输出值为null的标签
 * 注意在使用的时候，记得将转换器注册到非常低的位置：xStream.registerConverter(nullConverter, XStream.PRIORITY_VERY_LOW);
 * @author : yuxia
 * @date : 2020/3/19
 */
public class NullConverter extends ReflectionConverter {
    public NullConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
        super(mapper, reflectionProvider);
    }

    public NullConverter(Mapper mapper, ReflectionProvider reflectionProvider, Class type) {
        super(mapper, reflectionProvider, type);
    }

    @Override
    protected void doMarshal(final Object source, final HierarchicalStreamWriter writer,
                             final MarshallingContext context) {
        final List fields = new ArrayList();
        final Map defaultFieldDefinition = new HashMap();
        final Class sourceType = source.getClass();

        // Attributes might be preferred to child elements ...
        reflectionProvider.visitSerializableFields(source, new ReflectionProvider.Visitor() {
            final Set writtenAttributes = new HashSet();

            @Override
            public void visit(String fieldName, Class type, Class definedIn, Object value) {
                if (!mapper.shouldSerializeMember(definedIn, fieldName)) {
                    return;
                }
                if (!defaultFieldDefinition.containsKey(fieldName)) {
                    Class lookupType = source.getClass();
                    // See XSTR-457 and OmitFieldsTest
                    if (definedIn != sourceType
                            && !mapper.shouldSerializeMember(lookupType, fieldName)) {
                        lookupType = definedIn;
                    }
                    defaultFieldDefinition.put(
                            fieldName, reflectionProvider.getField(lookupType, fieldName));
                }

                SingleValueConverter converter = mapper.getConverterFromItemType(
                        fieldName, type, definedIn);
                if (converter != null) {
                    final String attribute = mapper.aliasForAttribute(mapper.serializedMember(
                            definedIn, fieldName));
                    if (value != null) {
                        if (writtenAttributes.contains(fieldName)) {
                            ConversionException exception =
                                    new ConversionException("Cannot write field as attribute for object, attribute name already in use");
                            exception.add("field-name", fieldName);
                            exception.add("object-type", sourceType.getName());
                            throw exception;
                        }
                        final String str = converter.toString(value);
                        if (str != null) {
                            writer.addAttribute(attribute, str);
                        }
                    }
                    writtenAttributes.add(fieldName);
                } else {
                    fields.add(new FieldInfo(fieldName, type, definedIn, value));
                }
            }
        });

        new Object() {
            {
                final Map hiddenMappers = new HashMap();
                for (Iterator fieldIter = fields.iterator(); fieldIter.hasNext(); ) {
                    FieldInfo info = (FieldInfo) fieldIter.next();
                    if (info.value != null) {
                        final Field defaultField = (Field) defaultFieldDefinition.get(info.fieldName);
                        Mapper.ImplicitCollectionMapping mapping = mapper
                                .getImplicitCollectionDefForFieldName(
                                        defaultField.getDeclaringClass() == info.definedIn ? sourceType : info.definedIn,
                                        info.fieldName);
                        if (mapping != null) {
                            Set mappings = (Set) hiddenMappers.get(info.fieldName);
                            if (mappings == null) {
                                mappings = new HashSet();
                                mappings.add(mapping);
                                hiddenMappers.put(info.fieldName, mappings);
                            } else {
                                if (!mappings.add(mapping)) {
                                    mapping = null;
                                }
                            }
                        }
                        if (mapping != null) {
                            if (context instanceof ReferencingMarshallingContext) {
                                if (info.value != Collections.EMPTY_LIST
                                        && info.value != Collections.EMPTY_SET
                                        && info.value != Collections.EMPTY_MAP) {
                                    ReferencingMarshallingContext refContext = (ReferencingMarshallingContext) context;
                                    refContext.registerImplicit(info.value);
                                }
                            }
                            final boolean isCollection = info.value instanceof Collection;
                            final boolean isMap = info.value instanceof Map;
                            final boolean isEntry = isMap && mapping.getKeyFieldName() == null;
                            final boolean isArray = info.value.getClass().isArray();
                            for (Iterator iter = isArray
                                    ? new ArrayIterator(info.value)
                                    : isCollection ? ((Collection) info.value).iterator() : isEntry
                                    ? ((Map) info.value).entrySet().iterator()
                                    : ((Map) info.value).values().iterator(); iter.hasNext(); ) {
                                Object obj = iter.next();
                                final String itemName;
                                final Class itemType;
                                if (obj == null) {
                                    itemType = Object.class;
                                    itemName = mapper.serializedClass(null);
                                } else if (isEntry) {
                                    final String entryName = mapping.getItemFieldName() != null
                                            ? mapping.getItemFieldName()
                                            : mapper.serializedClass(Map.Entry.class);
                                    Map.Entry entry = (Map.Entry) obj;
                                    ExtendedHierarchicalStreamWriterHelper.startNode(
                                            writer, entryName, entry.getClass());
                                    writeItem(entry.getKey(), context, writer);
                                    writeItem(entry.getValue(), context, writer);
                                    writer.endNode();
                                    continue;
                                } else if (mapping.getItemFieldName() != null) {
                                    itemType = mapping.getItemType();
                                    itemName = mapping.getItemFieldName();
                                } else {
                                    itemType = obj.getClass();
                                    itemName = mapper.serializedClass(itemType);
                                }
                                writeField(
                                        info.fieldName, itemName, itemType, info.definedIn, obj);
                            }
                        } else {
                            writeField(
                                    info.fieldName, null, info.type, info.definedIn, info.value);
                        }
                    }else{
                        // 处理null值的标签也输出
                        writeField(info.fieldName,null,info.type,info.definedIn,"");
                    }
                }

            }

            void writeField(String fieldName, String aliasName, Class fieldType,
                            Class definedIn, Object newObj) {
                Class actualType = newObj != null ? newObj.getClass() : fieldType;
                ExtendedHierarchicalStreamWriterHelper.startNode(writer, aliasName != null
                        ? aliasName
                        : mapper.serializedMember(sourceType, fieldName), actualType);

                if (newObj != null) {
                    Class defaultType = mapper.defaultImplementationOf(fieldType);
                    if (!actualType.equals(defaultType)) {
                        String serializedClassName = mapper.serializedClass(actualType);
                        if (!serializedClassName.equals(mapper.serializedClass(defaultType))) {
                            String attributeName = mapper.aliasForSystemAttribute("class");
                            if (attributeName != null) {
                                writer.addAttribute(attributeName, serializedClassName);
                            }
                        }
                    }

                    final Field defaultField = (Field) defaultFieldDefinition.get(fieldName);
                    if (defaultField.getDeclaringClass() != definedIn) {
                        String attributeName = mapper.aliasForSystemAttribute("defined-in");
                        if (attributeName != null) {
                            writer.addAttribute(
                                    attributeName, mapper.serializedClass(definedIn));
                        }
                    }

                    Field field = reflectionProvider.getField(definedIn, fieldName);
                    marshallField(context, newObj, field);
                }
                writer.endNode();
            }

            void writeItem(Object item, MarshallingContext context,
                           HierarchicalStreamWriter writer) {
                if (item == null) {
                    String name = mapper.serializedClass(null);
                    ExtendedHierarchicalStreamWriterHelper.startNode(
                            writer, name, Mapper.Null.class);
                    writer.endNode();
                } else {
                    String name = mapper.serializedClass(item.getClass());
                    ExtendedHierarchicalStreamWriterHelper.startNode(
                            writer, name, item.getClass());
                    context.convertAnother(item);
                    writer.endNode();
                }
            }
        };
    }

    private static class FieldInfo extends FieldLocation {
        final Class type;
        final Object value;

        FieldInfo(final String fieldName, final Class type, final Class definedIn, final Object value) {
            super(fieldName, definedIn);
            this.type = type;
            this.value = value;
        }
    }

    private static class FieldLocation {
        final String fieldName;
        final Class definedIn;

        FieldLocation(final String fieldName, final Class definedIn) {
            this.fieldName = fieldName;
            this.definedIn = definedIn;
        }

        @Override
        public int hashCode() {
            final int prime = 7;
            int result = 1;
            result = prime * result + (definedIn == null ? 0 : definedIn.getName().hashCode());
            result = prime * result + (fieldName == null ? 0 : fieldName.hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final NullConverter.FieldLocation other = (NullConverter.FieldLocation) obj;
            if (definedIn != other.definedIn) {
                return false;
            }
            if (fieldName == null) {
                if (other.fieldName != null) {
                    return false;
                }
            } else if (!fieldName.equals(other.fieldName)) {
                return false;
            }
            return true;
        }
    }


}
