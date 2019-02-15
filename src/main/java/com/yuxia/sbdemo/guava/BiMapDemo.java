package com.yuxia.sbdemo.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

public class BiMapDemo {
    @Test
    public void test1() {
        BiMap biMap = HashBiMap.create();
        biMap.put("a", "aaa");
        biMap.put("b", "bbb");
        biMap.put("c", "ccc");
        biMap.put("c", "cccc");
        biMap.forcePut("b", "cccc");
        System.out.println(biMap);
        System.out.println(biMap.values());

        System.out.println(biMap.inverse());
        System.out.println(biMap.inverse().values());
    }
@Test
public void test(){
    double A  = Double.NaN;
    System.out.println("A==A ="+(A==A));
}
}
