package com.yuxia.sbdemo.stream;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

/**
*  对Stream的排序通过sorted进行，它比数组的排序更强之处在于你可以首先对Stream进行各类
 * filter、limit、skip甚至distinct来减少元素数量后再排序，这能帮助程序明显缩短执行时间
* @Author:  xiaolei
* @Date:  on  2019/3/7 16:36
*/
public class SortedDemo {

    /**
     * 注意顺序，顺序不一样，结果也可能不同
     */
    @Test
    public void test(){
        List<Integer> list = ImmutableList.of(7,2,3,1000,-1,0,29,6,5,9,4,8,1);
        list.stream().filter(num->num<=9&&num>=0).sorted().limit(10).skip(0).forEach(System.out::println);
        System.out.println("--");
        list.stream().limit(10).skip(0).filter(num->num<=9&&num>=0).sorted().forEach(System.out::println);
    }
}
