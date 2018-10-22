package io.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 陈钊 on 2017/1/4.
 *
 * @description
 */
public class TimeClientHandle implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    /**
     * 构造函数用于初始化NIO的Selector和SocketChannel对象。
     * @param host
     * @param port
     */
    public TimeClientHandle(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        try {
            //用于发送连接请求
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        /**
         * 在while循环中，轮询Selector，当有就绪的Channel时，handleInput（）方法。
         */
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey selectionKey = it.next();
                    it.remove();
                    try {
                        handleInput(selectionKey);
                    } catch (IOException e) {
                        if (selectionKey != null) {
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param selectionKey
     * @throws IOException
     */
    private void handleInput(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        if (selectionKey.isConnectable()) {
            if (socketChannel.finishConnect()) {
                //如果连接成功，将socket注册到selector上，监听OP_READ操作
                socketChannel.register(selector, SelectionKey.OP_READ);
                //发送消息到服务端
                doWrite(socketChannel);
            } else {
                System.exit(1);//连接失败，退出
            }
        }
        //有消息可读的时候
        if (selectionKey.isReadable()) {
            //分配1M的缓冲区
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            //读取服务端消息
            int readBytes = socketChannel.read(readBuffer);
            if (readBytes > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = new String(bytes, "utf-8");
                System.out.println("Now is " + body);
                this.stop = true;
            } else if (readBytes < 0) {
                selectionKey.cancel();
                socketChannel.close();
            } else {
                ;//读到0字节，忽略
            }
        }
    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
        byteBuffer.put(req);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            System.out.println("Send order 2 server succeed.");
        }
    }

}
