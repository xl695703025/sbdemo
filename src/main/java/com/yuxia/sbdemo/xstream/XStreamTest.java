package com.yuxia.sbdemo.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider;
import org.junit.jupiter.api.Test;

/**
 * @author : xiaolei
 * @date : 2019/5/21
 */
public class XStreamTest {
    @XStreamAlias("Age")
    private String age;
    @XStreamAlias("Name")
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

public static void main(String[] args) {
    Student stu = new Student();
    stu.setAge(1);
    stu.setName(null);
    XStream xStream = new XStream();
    // 使用注解
    xStream.autodetectAnnotations(true);
    // 不输出class信息
    xStream.aliasSystemAttribute(null, "class");
    NullConverter nullConverter = new NullConverter(xStream.getMapper(), new SunUnsafeReflectionProvider());
    // 将转换器注册到非常低的位置非常重要
    xStream.registerConverter(nullConverter,XStream.PRIORITY_VERY_LOW);
    System.out.println(xStream.toXML(stu));
}


    @Test
    public void test2(){
        XStream xStream  =new XStream();
        XStreamTest xStreamTest = new XStreamTest();
        xStreamTest.setAge("11");
        xStreamTest.setName(null);
        String xml = xStream.toXML(xStreamTest);
        System.out.println(xml);
    }
}
