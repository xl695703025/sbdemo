package com.yuxia.sbdemo.guava;

import com.google.common.base.Function;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;

public class MultimapDemo {

    @Test
    public void test1(){
        Multimap<String ,String> map  = HashMultimap.create();
        map.put("a","a");
        map.put("a","a");
        map.put("a","b");
        System.out.println(map);
    }
    @Test
    public void test2(){
        ImmutableMap<String, String> map1 = ImmutableMap.of("type", "blog", "id", "11", "author", "join");
        ImmutableMap<String, String> map2 = ImmutableMap.of("type", "article", "id", "11", "author", "join");
        List<ImmutableMap<String,String>> list = Lists.newArrayListWithExpectedSize(4);
        list.add(map1);
        list.add(map1);
        list.add(map2);
        list.add(map2);
        System.out.println(list);
        ImmutableListMultimap<String, ImmutableMap<String,String>> multimap = Multimaps.index(list, new Function<ImmutableMap<String,String>, String>() {
            @Override
            public String apply(ImmutableMap<String,String> input) {
                return input.get("type");
            }
        });
        System.out.println(multimap.get("blog"));
        System.out.println(multimap.get("article"));
    }
}
