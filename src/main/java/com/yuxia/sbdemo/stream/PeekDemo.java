package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 *  peek()相关的操作
* @Author:  xiaolei
* @Date:  on  2019/3/7 16:04
*/
public class PeekDemo {
    /**
     * Stream如果进行相关的操作，就不能再使用原来的引用了
     * 需要重新引用或者链式编程
     * Strem不是每一次操作都会执行一下
     * 只有触发了Terminal操作，会将所有的操作一起执行
     */
    @Test
    public void test(){
        List list = ImmutableList.of(1,2,3,4,5);
        Stream stream = list.stream();
        stream.peek(System.out::println)
                .peek(System.out::println)
                .forEach(System.out::println);
    }
}
