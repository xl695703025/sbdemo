package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
/**
*  skip()相关操作
* @Author:  xiaolei
* @Date:  on  2019/3/7 16:34
*/
public class SkipDemo {
    @Test
    public void test(){
        List list = ImmutableList.of(5,1,3,6,3,2,4);
        list.stream().skip(5).forEach(System.out::println);
    }
}
