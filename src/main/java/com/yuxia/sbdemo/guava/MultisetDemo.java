package com.yuxia.sbdemo.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.ArrayList;

public class MultisetDemo {
    @Test
    public void test1(){
        Multiset<Integer> multiset = HashMultiset.create();
        multiset.add(1);
        multiset.add(1);
        //添加2 10次
        multiset.add(2,10);
        multiset.add(3,10);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        //multiset.removeAll(list);
        // 只会删除一次
        multiset.remove(1);
        // 删除2两次
        multiset.remove(2,2);
        System.out.println(multiset);
    }
}
