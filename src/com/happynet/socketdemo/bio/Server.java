package com.happynet.socketdemo.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lly on 14/11/13.
 */
public class Server extends Thread {

    ServerSocket server=null;
    Socket socket=null;


    public Server() {
        try {
            server = new ServerSocket(1990);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
         while (true){
             System.out.println("listing......");
             try {
                 socket=server.accept();//如果没有新的socket连接上，此进程会一直停在此处，等待返回。
                 SocketServerThread sst=new SocketServerThread(socket);//一旦得到新的socket请求，就就给新的线程去做，主线程会继续下一个循环，再次等待。如果每个线程对内存和占用时间要求比较高，会导致服务器压力变大。
                 //线程最大数量的设置根据每个工作任务的所占内存和执行时间来决定，如果每个线程工作时间很短，可以开放更多的线程。反之需要降低服务器的并发线程数。
                 sst.start();
                 sleep(5000);
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
