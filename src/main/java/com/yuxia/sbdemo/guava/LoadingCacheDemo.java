package com.yuxia.sbdemo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoadingCacheDemo {
    @Test
    public void test1() throws Exception {
        // expireAfterWrite设置写入内存后30s后过期,第二次访问不会刷新过期时间
        // expireAfterAccess设置访问后30s后过期
        // maximumSize最大记录条数
        LoadingCache<Integer, String> cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .maximumSize(2)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        Map<Integer,String> map = Maps.newHashMapWithExpectedSize(3);
                        map.put(1,"yuxia");
                        map.put(2,"xl");
                        map.put(3,"yxl");
                        if(map.containsKey(key)){
                            System.out.println("查询数据成功");
                        }
                        return map.get(key);
                    }
                });
        System.out.println("查询数据1：");
        cache.get(1);
        System.out.println("查询数据2：");
        cache.get(2);
        System.out.println("查询数据3：");
        cache.get(3);
        System.out.println("查询数据1：");
        cache.get(1);
        Thread.sleep(4000);
        System.out.println("查询数据1：");
        cache.get(1);
    }
}
