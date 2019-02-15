package com.jiedaibao.sbdemo.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("A")
public class A<T> {
    @XStreamAlias("nameaaa")
    String name="aaa";

    //@XStreamImplicit(itemFieldName = "str")
    //@XStreamAlias("A")
    //List<B> b=new ArrayList<>();
//    @XStreamAlias("name")
//    String name="aa";
//    @XStreamAlias("BBBBBBBBB")
//    B bb=new B();
    //@XStreamAlias("body")
     T body;

}
