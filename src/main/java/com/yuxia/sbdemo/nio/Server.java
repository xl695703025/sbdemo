package com.yuxia.sbdemo.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
*  服务端：socket实现
* @Author:  xiaolei
* @Date:  on  2019/3/25 17:49
*/
public class Server {

    public static void server() {
        ServerSocket serverSocket = null;
        InputStream in = null;
        Thread.currentThread().setName("server");
        try {
            serverSocket = new ServerSocket(8080);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while (true) {
                Socket clntSocket = serverSocket.accept();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println(Thread.currentThread().getName()+":"+"Handling client at " + clientAddress);
                in = clntSocket.getInputStream();
                while ((recvMsgSize = in.read(recvBuf)) != -1) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(Thread.currentThread().getName()+":"+new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
