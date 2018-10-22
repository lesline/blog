package io.aio.server;


import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by 陈钊 on 2017/1/5.
 *
 * @description
 */
public class AcceptCompletionHandle implements CompletionHandler<AsynchronousSocketChannel,AsynTimeServerHandle>{

    @Override
    public void completed(AsynchronousSocketChannel result, AsynTimeServerHandle attachment) {
        System.out.println("有连接接入");
        //准备下一次连接请求
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer,buffer,new ReadCompletionHandle(result));
    }

    @Override
    public void failed(Throwable exc, AsynTimeServerHandle attachment) {
        exc.printStackTrace();
        attachment.countDownLatch.countDown();
    }
}
