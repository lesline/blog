package io.nio.client;

/**
 * Created by 陈钊 on 2017/1/4.
 *
 * @description
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        for(;;) {
            new Thread(new TimeClientHandle("127.0.0.1", port)).start();
        }
    }
}
