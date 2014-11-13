package com.happynet.socketdemo.bio;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lly on 14/11/13.
 */
public class Client extends Thread{

    Socket sk=null;
    BufferedReader reader=null;
    PrintWriter wtr=null;
    BufferedReader keyin=null;


    public Client(){
        keyin=new BufferedReader(new InputStreamReader(System.in));
        try{
            sk=new Socket("127.0.0.1",1990);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try{
            reader=new BufferedReader(new InputStreamReader(sk.getInputStream()));
            wtr=new PrintWriter(sk.getOutputStream());
            String getString=keyin.readLine();

            while (true){
                if (getString!=null&&getString.trim().length()>0){
                    wtr.println(getString);
                    wtr.flush();
                    System.out.println(" ======客户端发送结束======= ");
                }

                if (reader!=null){
                    String line=reader.readLine();
                    System.out.println("=====从服务器返回的数据= " + line);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                wtr.close();
                sk.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) {
        new Client().start();
    }
}
