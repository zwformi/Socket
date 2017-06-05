package com.zw.thread;

import com.zw.User;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/6/5.
 */
public class ServerThread extends Thread {
    // 创建一个和本线程相关的socket
    Socket socket = null;
    //当创建一个线程的时候，创建一个socket
    public ServerThread(Socket socket){
        this.socket=socket;


    }
    //启动线程，重写父类的方法

    public void run(){

        System.out.println("服务器端准备好监听客户端的请求了。。。");
        try {

            // 3:获得输入流
            InputStream is = socket.getInputStream();
            // 包装下变成字符流，实现可以对对象进行反序列话操作
            ObjectInputStream ois=new ObjectInputStream(is);
            //创建输出流，用来给客户端提示的响应信息
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            // 4:读取对象，获得对象
            User user=(User)ois.readObject();
            System.out.println("编号："+user.getId());
            System.out.println("年龄："+user.getAge());
            System.out.println("姓名："+user.getName());
            System.out.println("密码："+user.getPassword());
            //给客户端的回应信息
            String response="客户端已经收到你们的信息了,Success";
            pw.write(response);
            pw.flush();
            // 5:关闭资源,注意，关闭的时候，要从底部开始往上依次关闭，顺序不能错！
            os.close();
            pw.close();
            ois.close();
            is.close();
            socket.close();

        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }


    }
}
