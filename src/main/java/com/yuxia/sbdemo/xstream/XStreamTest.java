package com.yuxia.sbdemo.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.junit.Test;

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

    @Test
    public void test(){
        XStream xStream  =new XStream();
        XStreamTest xStreamTest = new XStreamTest();
        xStreamTest.setAge("11");
        xStreamTest.setName(null);
        String xml = xStream.toXML(xStreamTest);
        System.out.println(xml);
    }
}
