package com.yuxia.sbdemo.guava;

import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

public class StringsDemo {
    @Test
    public void test1() {
        //获取相同前缀
        String pre = Strings.commonPrefix("aaabcd", "aaaddd");
        System.out.println(pre);
        //获取相同的后缀
        String suf = Strings.commonSuffix("asddd", "csddd");
        System.out.println(suf);

        //字符串补全
 String padEndResult = Strings.padEnd("123", 5, '0');
        System.out.println("padEndResult is " + padEndResult);
        String padStartResult = Strings.padStart("1", 4, '0');
        System.out.println("padStartResult is " + padStartResult);
    }
}
