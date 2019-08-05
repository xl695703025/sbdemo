package com.yuxia.sbdemo.junit.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * junit测试demo
 * @author : xiaolei
 * @date : 2019/08/05
 */
public class TestDemo {
    public int add(int a,int b){
        return a+b;
    }
    @ParameterizedTest
    @DisplayName("测试add")
    @CsvSource({"1,2,3","3,4,7"})
    public void test(int a,int b,int r){
        TestDemo td = new TestDemo();
        int res = td.add(a, b);
        Assertions.assertEquals(res,r);
    }
}
