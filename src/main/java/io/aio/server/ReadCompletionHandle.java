package io.aio.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * Created by 陈钊 on 2017/1/5.
 *
 * @description
 */
public class ReadCompletionHandle implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel asynchronousSocketChannel;

    public ReadCompletionHandle(AsynchronousSocketChannel asynchronousSocketChannel) {
        if (this.asynchronousSocketChannel == null) {
            this.asynchronousSocketChannel = asynchronousSocketChannel;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        System.out.println("服务端读取消息");
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body,"utf-8");
            System.out.println("The Time server receive order:"+req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req)?new Date().toString():"BAD ORDER";
            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void doWrite(String currentTime) {
        if(currentTime!=null&&currentTime.trim().length()>0){
            byte[] bytes = currentTime.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            asynchronousSocketChannel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if(attachment.hasRemaining()){
                        asynchronousSocketChannel.write(attachment,attachment,this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        asynchronousSocketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.asynchronousSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
