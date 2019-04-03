package com.yuxia.sbdemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
*  客户端NIO实现
* @Author:  xiaolei
* @Date:  on  2019/3/25 17:49
*/
public class Client {
    public static void client() {
        // 申请一块空间
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        Thread.currentThread().setName("client");
        try {
            // 打开一个Channel
            socketChannel = SocketChannel.open();
            //设置为非阻塞
            socketChannel.configureBlocking(false);
            //连接IP和端口号
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    // 为了不让消息发送太快，每发一条睡1s
                    TimeUnit.SECONDS.sleep(1);
                    String info = Thread.currentThread().getName()+":I'm " + i++ + "-th information from client";
                    //清空Buffer
                    buffer.clear();
                    //写入到Buffer中
                    buffer.put(info.getBytes());
                    //进行flip操作，为了下面可以将buffer中数据读取到channel中。
                    buffer.flip();
                    // 将buffer中的数据写入到channel中
                    while (buffer.hasRemaining()) {
                        System.out.println(Thread.currentThread().getName()+":"+buffer);
                        int write = socketChannel.write(buffer);
                        System.out.println(Thread.currentThread().getName()+":"+write);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
