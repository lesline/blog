package io.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemMap {
    private static String PATH = "/Users/zhangshaolin/Downloads/data-20180831-033734.xls";

    /**
     * 使用内存映射方式读取文件总耗时
     *
     * @throws IOException
     */
    @Test
    public void execByMemMappe() throws IOException {
        RandomAccessFile file = new RandomAccessFile(PATH, "rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        byte[] b = new byte[1024];

        long len = file.length();
        long startTime = System.currentTimeMillis();
        //读取内存映射文件
        for (int i = 0; i < file.length(); i += 1024 * 10) {
            if (len - i > 1024) {
                buffer.get(b);
            } else {
                buffer.get(new byte[(int) (len - i)]);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用内存映射方式读取文件总耗时： " + (endTime - startTime));
    }

    /**
     * 普通IO流方式
     *
     * @throws IOException
     */
    @Test
    public void execByNormalIO() throws IOException {
        RandomAccessFile file = new RandomAccessFile(PATH, "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //普通IO流方式
        long startTime = System.currentTimeMillis();
        while (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            byteBuffer.clear();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("使用普通IO流方式读取文件总耗时： " + (endTime - startTime));
    }
}