package io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 陈钊 on 2017/1/4.
 *
 * @description
 */
public class MutiplexerTimeServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    /**
     * 构造方法，在构造方法中进行资源初始化，创建多路复用器selector,serverSocketChannel
     * 对channel和tcp参数进行配置。
     * 初始化成功后，将channel注册到selector，监听SelectionKey.OP_ACCEPT操作为
     * 如果初始化过程中出现异常，则退出
     * @param port
     */
    public MutiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The Time Server is Start on Port " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void stop() {
        this.stop = true;
    }

    /**
     * 在while循环中遍历selector，其休眠时间被设置为1s。无论是否有读写事件，selector都1s被唤醒一次
     * 当channel处于就绪状态时，selector将返回就绪状态的SelectionKey集合，通过对就绪状态的Channel集
     * 合进行迭代，可以进行异步读写操作。
     */
    @Override
    public void run() {
        while (!stop) {
            try {
                //1s唤醒一次
                selector.select(1000);
                //获取就绪状态的channel的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //使用迭代器进行遍历
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while (it.hasNext()) {
                    selectionKey = it.next();
                    it.remove();
                    try {
                        handleInput(selectionKey);
                    } catch (Exception e) {
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
            }
        }
    }

    /**
     * 该方法处理新接入的客户端请求
     * 根据selectionKey的操作位判断获得网络事件的类型。
     * 通过ServerSocketChannel.accept()接收客户端的连接请求并创建SocketChannel实例。
     * 完成上述操作后，相当于完成TCP三次握手，TCP物理链路正式建立。
     * @param selectionKey
     * @throws IOException
     */
    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            //处理新接入的请求消息
            if (selectionKey.isAcceptable()) {
                //Accept the new connection
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
            /**
             * 读取客户端请求信息
             * 创建一个有1k的缓冲区，然后调用SocketChannel.read()方法读取请求码流
             * read（）方法有三种返回值：
             * readBytes>0:读到了字节，可以对其进行编解码
             * readBytes=0：读到了0字节
             * readBytes<0:channel已经关闭
             */
            if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //将客户端数据读取到缓冲区，并返回读取到的字节数。
                int readBytes = socketChannel.read(readBuffer);
                //当可读字节>0时，处理客户端请求
                if (readBytes > 0) {
                    //flip操作的作用是将limt值设置为position，poition设置为0，用于对后续缓冲区的操作。
                    //不明白可以查看说明http://www.cnblogs.com/woshijpf/articles/3723364.html
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("The Time Server Receive Order；" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "Bad Order";
                    //回复客户端请求
                    doWrite(socketChannel, currentTime);
                } else if (readBytes < 0) {
                    selectionKey.cancel();
                    socketChannel.close();
                } else {
                    //读到0字节，忽略
                }
            }
        }
    }

    /**
     * 将回复写出到客户端
     * 将字符串编码为字节数组，再根据数组大小创建缓冲区，put方法将字节数组复制到缓冲区
     * 然后对缓冲区进行flip操作，最后write方法（）将回复发送出去。
     * SocketChannel是异步非阻塞的，不能保证一次能够将所有字节发送完，需要处理。
     * @param socketChannel
     * @param data
     * @throws IOException
     */
    private void doWrite(SocketChannel socketChannel, String data) throws IOException {
        if (data != null && data.trim().length() > 0) {
            byte[] bytes = data.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        }
    }
}
