package com.yuxia.sbdemo.thread;

import org.junit.Test;

/**
 * junit多线程测试的会出问题<br>
 * 现象：多线不执行或者执行到中途就结束了<br>
 * 猜测：可能由于主线程执行完毕，导致子线程也终止了
 * 分析：通过对junit源码分析，发现主线程执行完毕后会调用System.exit()，会关闭JVM<br>
 * JVM都关闭了，子线程也不可能继续执行了<br>
 * 结论：在junit中，主线程执行完毕，会调用System.exit()关闭JVM，会导致子线程无法继续执行
 *
 * @author : xiaolei
 * @date : 2019/4/22
 */
public class JunitThreadBugTest {

    @Test
    public void test() throws InterruptedException {
        System.out.println("test start");
        new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while (i<100){
                    System.out.print(i++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //主线程睡10s，子线程就会输出10次(不保证一定10次)，主线程执行完毕后，子线程也会停止输出
        Thread.sleep(10000);
        System.out.println("test end");
    }
}
