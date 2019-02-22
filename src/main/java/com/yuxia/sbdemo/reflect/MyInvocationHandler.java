package com.yuxia.sbdemo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
* @Description:  JDK动态代理的实现
 * 什么是代理模式？例如找房子，我们可以自己找，也可以找中介代理去找
 * 如果是自己找，首先要找到房源，然后看房、买房、签协议。
 * 如果是代理模式，房源就叫给中介代理去找，我们只需负责看房、买房、签协议就好。
 * 可以省去找房源这个麻烦的事情。
* @Author:  xiaolei
* @Date:  on  2019/2/22 10:05
*/
public class MyInvocationHandler implements InvocationHandler {
    //被代理对象
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 被代理对象执行前
        System.out.println("中介找房子~!");
        // 被代理对象执行
        Object result = method.invoke(target, args);
        // 被代理对象执行后
        System.out.println("中介处理后续的事情");
        return result;
    }
    public IDoSometing getProxy(){
        // 返回代理对象
        return (IDoSometing) Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
    public static void main(String[] args) {
        IDoSometing doSometing = new MyInvocationHandler(new DoSometing()).getProxy();
        doSometing.doSometing();
    }
}
