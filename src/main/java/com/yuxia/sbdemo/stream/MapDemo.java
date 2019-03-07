package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 和map()相关的一些操作
 *
 * @Author: xiaolei
 * @Date: on  2019/3/7 14:36
 */
public class MapDemo {
    /**
     * map入参是一个Function
     * 将输入的每个参数按照Function映射成另一个参数
     */
    @Test
    public void mapTest() {
        List<Integer> nums = ImmutableList.of(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squareNums);
    }

    /**
     * flatMap入参也是一个Function，不要求返回值必须继承于Stream()
     * flatMap会将所有的stream合并在一起
     */
    @Test
    public void flatMapTest() {
        List<List<Integer>> lists = ImmutableList.of(
                ImmutableList.of(1, 2, 3, 4),
                ImmutableList.of(5, 6),
                ImmutableList.of(2, 7)
        );
        lists.stream().flatMap(childList -> childList.stream()).forEach(num -> System.out.println(num));
        //lists.stream().map(childList-> childList.stream()).forEach(num-> System.out.println(num));
    }
}
