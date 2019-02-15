package com.jiedaibao.sbdemo.test;

import java.text.ParseException;

//@XStreamAlias("body")
public class C  {
        String name;
        Integer age=222;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new C().getName()+"ss");
    }
}
