package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
*  forEach()相关操作
* @Author:  xiaolei
* @Date:  on  2019/3/7 16:00
*/
public class ForEachDemo {
    /**
     * forEach()是terminal操作，执行后Stream就被消费了
     * 再次执行terminal操作会报错
     */
    @Test
    public void test1(){
        ImmutableList list = ImmutableList.of(1,2,3,4,5);
        Stream stream = list.stream();
        stream.forEach(System.out::println);
        //再次执行会报错
        //stream.forEach(System.out::println);
    }
}
