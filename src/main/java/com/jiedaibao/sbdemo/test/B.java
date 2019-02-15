package com.jiedaibao.sbdemo.test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;

@XStreamAlias("ListB")
public class B  extends C{
    @XStreamAlias("namebbbb")
    String name="bbb";

    public static void main(String[] args) {
        XStream magicApi2 = new XStream();
        magicApi2.registerConverter(new MapEntryConverter());
        magicApi2.autodetectAnnotations(true);
        //magicApi2.aliasSystemAttribute(null,"class");
        A a=new A();
        a.body=new ArrayList<B>();
        ((ArrayList) a.body).add(new B());
        String xmlStr = magicApi2.toXML(a);
        System.out.println(xmlStr);
        xmlStr.replace("<ListB>","<ListB  class='com.jiedaibao.sbdemo.test.B'>");
        magicApi2.processAnnotations(A.class);
        magicApi2.processAnnotations(B.class);
        magicApi2.processAnnotations(ArrayList.class);
        magicApi2.processAnnotations(Object.class);
        a=(A)magicApi2.fromXML(xmlStr);
    }
}
