package com.yuxia.sbdemo.guava;

import com.google.common.base.Throwables;
import org.junit.Test;

import java.sql.SQLException;

public class ThrowablesDemo {
    @Test
    public void test() {

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
