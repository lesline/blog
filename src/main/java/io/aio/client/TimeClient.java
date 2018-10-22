package io.aio.client;

/**
 * Created by 陈钊 on 2017/1/5.
 *
 * @description
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        new Thread(new AsynTimeClientHandle("127.0.0.1",port),"AIO-TIMECLIENT").start();
    }
}
