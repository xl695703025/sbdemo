package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

/**
*  filter()相关的操作
* @Author:  xiaolei
* @Date:  on  2019/3/7 15:15
*/
public class FilterDemo {

    /**
     * filter入参是一个Predicate
     * 会根据Predicate进行筛选
     */
    @Test
    public void test1(){
        ImmutableList<Integer> list = ImmutableList.of(1, 2, 3, 4, 5, 6);
        list.stream().filter(num->num>3).forEach(System.out::println);
    }
}
