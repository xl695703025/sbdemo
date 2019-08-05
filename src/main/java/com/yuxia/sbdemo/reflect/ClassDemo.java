package com.yuxia.sbdemo.reflect;

import org.junit.jupiter.api.Test;

/**
* @Description:  一些关于Class的Demo
 * 类加载分为加载->连接->初始化   其中连接为验证->准备->解析
 * Class.class时不会进行初始化， forName()才会初始化
 * 用类名引用static修饰的变量会进行初始化
 * 用类名引用final static 修饰的变量不会进行初始化
* @Author:  xiaolei
* @Date:  on  2019/2/19 14:10
*/
public class ClassDemo {

    @Test
    public void test1(){
        System.out.println(TestClass1.name);
        System.out.println("-----------------");
        System.out.println(TestClass2.name);
        System.out.println("-----------------");
        System.out.println(TestClass3.name);
        System.out.println("-----------------");
    }
    @Test
    public void test2() throws ClassNotFoundException {
        System.out.println(TestClass2.class);
        System.out.println("-----------------");
        System.out.println(Class.forName("com.yuxia.sbdemo.reflect.TestClass2"));
        System.out.println("-----------------");
        System.out.println(Class.forName("com.yuxia.sbdemo.reflect.TestClass3"));
        System.out.println("-----------------");
    }
}

class TestClass1 {
    //只是static修饰
    public static String name = "yuxia";

    static {
        System.out.println("TestClass1静态块");
    }

    public TestClass1() {
        System.out.println("TestClass1构造了");
    }
}
class TestClass2 {
    //注意是final staitc修饰的
    public final static String name = "yuxia";

    static {
        System.out.println("TestClass2静态块");
    }

    public TestClass2() {
        System.out.println("TestClass2构造了");
    }
}

class TestClass3 {
    //注意是final staitc修饰的
    public final static String name;

    static {
        name = "yuxia";
        System.out.println("TestClass3静态块");
    }

    public TestClass3() {
        System.out.println("TestClass3构造了");
    }
}
