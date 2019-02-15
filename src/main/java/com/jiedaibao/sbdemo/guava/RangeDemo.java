package com.jiedaibao.sbdemo.guava;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import org.junit.Test;

public class RangeDemo {
    @Test
    public void test1(){
        Range<Integer> range = Range.open(1,10);
        printRange(range);
    }
    private void printRange(Range<Integer> range){
        System.out.print("[ ");
        for(int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade +" ");
        }
        System.out.println("]");
    }
}
