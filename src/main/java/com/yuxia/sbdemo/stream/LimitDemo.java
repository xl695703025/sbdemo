package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

/**
*  Limit()相关操作
 *  注意，如果Stream是有序的且是parallelStream，用Limit()就会开销很大
 *  因为他要保证截取的前n也是有序的，这种时候就别用parallelStream或者不进行排序
* @Author:  xiaolei
* @Date:  on  2019/3/7 16:30
*/
public class LimitDemo {
    @Test
    public void test(){
        List list = ImmutableList.of(5,1,3,6,3,2,4);
        list.stream().limit(5).forEach(System.out::println);
    }
}
