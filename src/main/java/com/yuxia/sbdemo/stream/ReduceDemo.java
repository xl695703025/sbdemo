package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

/**
*  reduce()相关操作
 *  reduce是给定（一个初始值和）BinaryOperator
 *  然后遍历和后面的值进行给定的BinaryOperator操作
 *  如果不给初始值，返回Optional
* @Author:  xiaolei
* @Date:  on  2019/3/7 16:17
*/
public class ReduceDemo {
    @Test
    public void test1(){
        List<Integer> integerList = ImmutableList.of(1,2,3,4,5);
        List<String>  stringList = ImmutableList.of("a","b","c");
        Integer sum = integerList.stream().reduce(10, Integer::sum);
        System.out.println(sum);
        String ss = stringList.stream().reduce("begin:", String::concat);
        System.out.println(ss);
    }

    /**
     * 不给定初始值，有可能没有足够的元素，可能为空
     * 所以返回Optional
     */
    @Test
    public void test2(){
        List<Integer> integerList = ImmutableList.of(1,2,3,4,5);
        List<String>  stringList = ImmutableList.of("a","b","c");
        Optional<Integer> sum = integerList.stream().reduce(Integer::sum);
        sum.ifPresent(System.out::println);
        stringList.stream().reduce(String::concat).ifPresent(System.out::println);
    }
}
