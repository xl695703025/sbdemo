package com.yuxia.sbdemo.guava;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

/**
 * Guava不可变集合
 * 以下演示了ImmutableSet三中创建方式(其它的不可变集合类似)
 * 不可变集合是不能进行修改的，会抛异常
 * @Author: xiaolei
 * @Date: on  2019/3/7 14:44
 */
public class ImmutableDemo {
    @Test
    public void test1() {
        Set<String> immutableNamedColors = ImmutableSet.<String>builder()
                .add("red", "green", "black", "white", "grey")
                .build();
        //进行添加会抛异常
        //immutableNamedColors.add("abc");
        for (String color : immutableNamedColors) {
            System.out.println(color);
        }
    }
    @Test
    public void test2() {
        Set<String> immutableNamedColors = ImmutableSet.of("red","green","black","white","grey");
        for (String color : immutableNamedColors) {
            System.out.println(color);
        }
    }
    @Test
    public void test3() {
        Set<String> immutableNamedColors =  ImmutableSet.copyOf(new String[]{"red","green","black","white","grey"});
        for (String color : immutableNamedColors) {
            System.out.println(color);
        }
    }

    /**
     * Guava的不可变集合asList()视图也是不可变的
     * 这个视图和Arrays.asList()有区别,Array.asList()返回的是一个静态内部类
     * Guava返回的是ImmutableList
     */
    @Test
    public void test4() {
        ImmutableSet<String> immutableNamedColors =  ImmutableSet.of("red","green","black","white","grey");
        List<String> colors = immutableNamedColors.asList();
        //会抛异常
        //colors.add("s");
        for (String color : colors) {
            System.out.println(color);
        }
    }
}
