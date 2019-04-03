package com.yuxia.sbdemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务端NIO实现
 *
 * @Author: xiaolei
 * @Date: on  2019/3/25 17:50
 */
public class ServerConnect {
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;

    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try {
            // 打开一个Slectore
            selector = Selector.open();
            // 打开一个Channel
            ssc = ServerSocketChannel.open();
            // 将Channel绑定端口
            ssc.socket().bind(new InetSocketAddress(PORT));
            // 设置Channel为非阻塞，如果设置为阻塞，其实和BIO差不多了。
            ssc.configureBlocking(false);
            // 向selector中注册Channel和感兴趣的事件
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                // selector监听事件，select会被阻塞，直到selector监听的channel中有事件发生或者超时，会返回一个事件数量
                //TIMEOUT就是超时时间，selector初始化的时候会添加一个用于主动唤醒的pipe，待会源码分析会说
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("==");
                    continue;
                }
                selector.wakeup();
                /**
                 * SelectionKey的组成是selector和Channel
                 * 有事件发生的channel会被包装成selectionKey添加到selector的publicSelectedKeys属性中
                 * publicSelectedKeys是SelectionKey的Set集合
                 *下面这一部分遍历，就是遍历有事件的channel
                 */
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    // 新连接的事件
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    // 读事件
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    // 写事件
                    if (key.isWritable() && key.isValid()) {
                        handleWrite(key);
                    }
                    // 连接事件
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    //每次使用完，必须将该SelectionKey移除，否则会一直存储在publicSelectedKeys中
                    //下一次遍历又会重复处理
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selector != null) {
                    selector.close();
                }
                if (ssc != null) {
                    ssc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void handleAccept(SelectionKey key) throws IOException {
        // 获取新连接上来的channel
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        // 接受与此通道的套接字建立的连接
        SocketChannel sc = ssChannel.accept();
        // 设置该channel为非阻塞的
        sc.configureBlocking(false);
        // 将这channel注册在selector中，并且监听读事件，申请一块Buffer，以附件的形式添加在SelectionKey中
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }
    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        //获取附件中的buffer
        ByteBuffer buf = (ByteBuffer) key.attachment();
        // 下面就是不断的读取channel中的内容，不多解释
        long bytesRead = sc.read(buf);
        while (bytesRead > 0) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if (bytesRead == -1) {
            sc.close();
        }
    }
    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }
}