package com.zw.client;

import com.zw.User;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017/6/5.
 */
public class ClientSocketTest1 {

    public static void main(String[] args){
        try {
            // 1,创建serverScoket客户端，绑定服务器端的ip以及端口
            Socket scoket = new Socket("localhost", 8800);
            // 2,得到scoket读写流
            OutputStream os = scoket.getOutputStream();
            // 获得对象输出流，可以对对象进行序列化操作
            ObjectOutputStream oos=new ObjectOutputStream(os);
            //输入流
            InputStream is=scoket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            // 3,利用一定的协议对scoket中的对读写操作
            PrintWriter pw = new PrintWriter(os);
            User user=new User();
            user.setId(1);
            user.setAge(19);
            user.setName("tom");
            user.setPassword("AAbb123");
            oos.writeObject(user);
            //需要先关闭，一定要记得先关闭
            scoket.shutdownOutput();
            //接收并显示收到服务器端返回的信息！
            String answer = null;
            while (!((answer = br.readLine()) == null)) {
                System.out.println("我是服务器端接收并且给返回的信息：\n" + answer);

            }
            // 4,关闭流

            is.close();
            oos.close();
            br.close();
            os.close();
            scoket.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println("客户端请求完毕！");
    }

}
