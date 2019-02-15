package com.yuxia.sbdemo.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class OrderingDemo {
    @Test
    public void test1(){
        List<Integer> list = Lists.newArrayListWithExpectedSize(7);
        list.add(5);
        list.add(2);
        list.add(15);
        list.add(51);
        list.add(53);
        list.add(35);
        list.add(45);
        System.out.println("输入值："+list);

        //自然排序
        Ordering ordering = Ordering.natural();
        Collections.sort(list,ordering);
        System.out.println("自然排序后："+list);
        //反序
        Collections.sort(list,ordering.reverse());
        System.out.println("反序后："+list);
    }
}
