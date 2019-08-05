package com.yuxia.sbdemo.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

public class JoinerDemo {
    @Test
    public void test1(){
        String s= Joiner.on("-").join(Arrays.asList("a","b","c","d","e"));
        System.out.println(s);
    }
    @Test
    public void test2(){
        Map map = Maps.newHashMapWithExpectedSize(2);
        map.put("a","A");
        map.put("b","B");
        String s = Joiner.on(",")
                .withKeyValueSeparator("=")
                .join(map);
        System.out.println(s);
    }
}
