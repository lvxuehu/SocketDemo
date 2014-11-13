package com.happynet.socketdemo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lly on 14/11/13.
 */
public class SocketServerThread extends Thread {
    Socket socket=null;
    BufferedReader rdr=null;
    PrintWriter wtr=null;

    public  SocketServerThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            rdr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            wtr=new PrintWriter(socket.getOutputStream());
            String line =rdr.readLine();
            System.out.println("从客户端传送过来的数据 = " + line+"服务器端负责处理的线程名称："+this.getName());

            wtr.println("你好，服务器已经收到您的信息！" + line + "\n");
            wtr.flush();
            System.out.println("已经返回给客户端！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rdr.close();
                wtr.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
