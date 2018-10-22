package io.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 陈钊 on 2017/1/3.
 *
 * @description
 */
public class TimeServer {
    public static void main(String[] args){
        TimeServerHandleExecutePool handleExecutePool = new TimeServerHandleExecutePool(50,1000);
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server socket started on port"+port);
            Socket socket = null;//= serverSocket.accept();
            while (true){
                socket = serverSocket.accept();
                handleExecutePool.execute(new TimeServerHandle(socket));
            }
        } catch (IOException e) {
           System.out.println("socket连接异常");
        }finally {
            if(serverSocket!=null){
                try {
                    System.out.println("server socket 断开连接");
                    serverSocket.close();
                    serverSocket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
