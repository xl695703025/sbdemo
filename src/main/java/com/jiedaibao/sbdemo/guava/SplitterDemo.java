package com.jiedaibao.sbdemo.guava;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class SplitterDemo {
    @Test
    public void test1(){
        String s="hello,word,,世界，             水平             ";
        List<String> list = Splitter.onPattern("[,，]")
                .omitEmptyStrings()//删除空白的字符串
                .trimResults()//删除字符串前后的空格
                .splitToList(s);
        for(String ss:list){
            System.out.println(ss);
        }
    }
    @Test
    public void test2(){
        String s = "a=A;b=B,c=C,d=D";
        Map<String, String> map = Splitter.onPattern("[,;]")
                .trimResults()
                .omitEmptyStrings()
                .withKeyValueSeparator('=')
                .split(s);
        for(Map.Entry e:map.entrySet()){
            System.out.println("KEY:"+e.getKey()+" VALUE:"+e.getValue());
        }
    }
}
