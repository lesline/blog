package io.aio.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 陈钊 on 2017/1/5.
 *
 * @description
 */
public class AsynTimeClientHandle implements Runnable,CompletionHandler<Void,AsynTimeClientHandle> {

    private AsynchronousSocketChannel socketChannel;
    private String host;
    private int port;
    private CountDownLatch countDownLatch;

    public AsynTimeClientHandle(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            socketChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        socketChannel.connect(new InetSocketAddress(host,port),this,this);
        System.out.println("Conneted to Server on port:"+port);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AsynTimeClientHandle attachment) {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        socketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if(attachment.hasRemaining()){
                    socketChannel.write(attachment,attachment,this);
                }else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] bytes = new byte[attachment.remaining()];
                             attachment.get(bytes);
                            String body;
                            try {
                                body = new String(bytes,"utf-8");
                                System.out.println("Now is :"+body);
                                countDownLatch.countDown();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                socketChannel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
                try {
                    socketChannel.close();
                    countDownLatch.countDown();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AsynTimeClientHandle attachment) {
        exc.printStackTrace();
        try {
            socketChannel.close();
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
