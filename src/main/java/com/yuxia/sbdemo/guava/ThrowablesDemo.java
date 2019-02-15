package com.yuxia.sbdemo.guava;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ThrowablesDemo {
    @Test
    public void test() {
        List<Integer> list = Lists.newArrayListWithExpectedSize(2);
        list.add(1);
        list.add(2);
        System.out.println(list.get(0));
        System.out.println(Iterators.get(list.iterator(),1));
    }

    /**
     * 该方法声明只抛出SQLException
     */
    public void doIt() throws SQLException {
        try {
            //可能会抛出不同类型的异常
            doSometing();
        } catch (Throwable throwable) {
            //如果throwable是SQLException的实例，则包装成SQLException抛出
            Throwables.throwIfInstanceOf(throwable, SQLException.class);
            //如果throwable是Error或者RuntimeException，则直接抛出
            //因为RuntimeException可以不需要声明
            Throwables.throwIfUnchecked(throwable);
        }
    }
    /**
     * 该方法声明抛出任何类型的Exception
     */
    public void doSometing() throws Exception {

    }
}
