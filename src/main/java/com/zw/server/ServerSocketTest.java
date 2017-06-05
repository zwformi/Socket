package com.zw.server;

import com.zw.thread.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/6/5.
 */
public class ServerSocketTest {

    public static void main(String[] args){
        try {
            // 1：创建一个服务端的ServerScoket,绑定端口开始监听
            ServerSocket serverScoket = new ServerSocket(8800);
            // 2:使用accept()方法阻塞等待连接，获得新的连接
            Socket socket = null;
            //加入一个统计来自客户端的请求的数目
            int num=0;
            //一直处于监听的运行转态
            while (true) {
                socket=serverScoket.accept();
                ServerThread serverThread=new ServerThread(socket);
                //启动线程
                serverThread.start();
                num++;
                System.out.println("客户端请求的次数为："+num);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
