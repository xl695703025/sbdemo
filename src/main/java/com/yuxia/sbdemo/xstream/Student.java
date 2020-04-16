package com.yuxia.sbdemo.xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author : xiaolei
 * @date : 2020/3/19
 */
@XStreamAlias("Student")
public class Student {
    @XStreamAlias("Age")
    private Integer age;
    @XStreamAlias("Name")
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
