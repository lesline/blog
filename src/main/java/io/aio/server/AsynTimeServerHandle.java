package io.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 陈钊 on 2017/1/5.
 *
 * @description
 */
public class AsynTimeServerHandle implements Runnable{
    private int port;
    CountDownLatch countDownLatch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsynTimeServerHandle(int port) {
        this.port = port;
        //创建一个服务端通道AsynchronousServerSocketChannel，然后调用bind方法绑定监听端口
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("Time Server start on port:"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //初始化CountDownLatch，作用是当前线程阻塞等待其他线程执行结束。

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        doAccept();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this,new AcceptCompletionHandle());
    }
}
