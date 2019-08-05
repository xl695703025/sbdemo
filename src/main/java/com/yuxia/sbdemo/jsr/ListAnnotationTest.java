package com.yuxia.sbdemo.jsr;

import com.google.common.collect.Lists;
import com.yuxia.sbdemo.utils.ParamUtil;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 测试注解用在list上
 * @author : xiaolei
 * @date : 2019/07/26
 */
public class ListAnnotationTest {

    public static void main(String[] args) {
        Person p  = new Person();
        p.setAge(10);
        p.setName("333");
        p.setEmail("0");
        System.out.println(p.getBookList());
        ParamUtil.validateParam(p);
    }
}
