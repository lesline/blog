package io.nio.server;

/**
 * Created by 陈钊 on 2017/1/4.
 *
 * @description
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        MutiplexerTimeServer timeServer = new MutiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
