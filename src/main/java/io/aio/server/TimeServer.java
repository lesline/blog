package io.aio.server;

/**
 * Created by 陈钊 on 2017/1/5.
 *
 * @description
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        AsynTimeServerHandle timeServerHandle = new AsynTimeServerHandle(port);
        new Thread(timeServerHandle,"AIO-AsynTimeServer").start();
    }
}
