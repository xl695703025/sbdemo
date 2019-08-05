package com.yuxia.sbdemo.guava;


import com.google.common.base.Optional;
import org.junit.jupiter.api.Test;

public class OptionalDemo {

    @Test
    public void test1(){
        Integer a = null;
        Integer b = 10;
        // 如果a为空返回Absent，否则返回Present
        Optional<Integer> oa = Optional.fromNullable(a);
        // 返回Present，如果b为空则抛NullPointerException
        Optional<Integer> ob = Optional.of(b);
        Integer value = sum(oa,ob);
        System.out.println(value);
    }

    private Integer sum(Optional<Integer> oa, Optional<Integer> ob){
        //如果oa为Absent则返回默认值，否则返回实例
        Integer a = oa.or(0);
        //返回实例
        Integer b= ob.get();
        return a+b;
    }

}
