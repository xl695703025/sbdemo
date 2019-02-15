package com.yuxia.sbdemo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Guava缓存
 * @Author: xiaolei
 * @Date: on  2019/2/15 15:32
 */
public class LoadingCacheDemo {
    /**
     * LRU(最近最久未使用)和超时过期
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        // expireAfterWrite设置写入内存后30s后过期,第二次访问不会刷新过期时间
        // expireAfterAccess设置访问后30s后过期
        // maximumSize最大记录条数
        // refreshAfterWrite指定时间没有被覆盖，将会刷新该缓存
        LoadingCache<Integer, String> cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .maximumSize(2)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        Map<Integer, String> map = Maps.newHashMapWithExpectedSize(3);
                        map.put(1, "yuxia");
                        map.put(2, "xl");
                        map.put(3, "yxl");
                        if (map.containsKey(key)) {
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

    /**
     * 定时刷新缓存
     * 注意，达到了刷新时间不会主动刷新缓存
     * 还需要进行get()操作
     * 才会刷新缓存
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Map<Integer, String> map = Maps.newHashMapWithExpectedSize(3);
        map.put(1, "yuxia");
        map.put(2, "xl");
        map.put(3, "yxl");
        LoadingCache<Integer, String> cache = CacheBuilder
                .newBuilder()
                .refreshAfterWrite(3, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        if (map.containsKey(key)) {
                            System.out.println("查询数据成功");
                        }
                        return map.get(key);
                    }
                });
        System.out.println(cache.get(1));
        map.put(1,"yuxiaaa");
        //在3s的时候并没有刷新缓存，只有get()操作的时候才进行刷新
        Thread.sleep(6000);
        System.out.println(cache.get(1));
    }
}
