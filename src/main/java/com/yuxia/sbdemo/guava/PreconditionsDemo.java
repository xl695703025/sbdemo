package com.yuxia.sbdemo.guava;

import com.google.common.base.Preconditions;
import org.junit.jupiter.api.Test;

public class PreconditionsDemo {
    @Test
    public void test1(){
        try {
            System.out.println(sum(1,null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int sum(Integer a, Integer b) {
        Preconditions.checkNotNull(a,"参数1为空");
        Preconditions.checkNotNull(b,"参数2为空");
        return a+b;
    }
    @Test
    public void test2(){
        int[] arr = {00,11,22,33,44};
        try {
            Preconditions.checkElementIndex(5,arr.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Preconditions.checkPositionIndex(6,arr.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
