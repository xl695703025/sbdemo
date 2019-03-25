package com.yuxia.sbdemo.nio;

import org.junit.Test;

/**
 * 测试类
 *
 * @Author: xiaolei
 * @Date: on  2019/3/25 17:50
 */
public class NIOTest {
    /**
     * 启动服务端socket
     */
    @Test
    public void test1() {
        Server.server();
    }

    /**
     * 启动客户端NIO
     */
    @Test
    public void test2() {
        Client.client();
    }

    /**
     * 启动服务端 NIO
     */
    @Test
    public void test3() {
        ServerConnect.selector();
    }
}
