package jdx.nio.filechannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散和聚集
 */
public class Demo4 {
    public static final Logger logger = LoggerFactory.getLogger(Demo4.class);

    public static void main(String[] args) {

        try (RandomAccessFile raf1 = new RandomAccessFile("D:\\Java\\NIO\\data\\1.txt", "rw");
             FileChannel channel1 = raf1.getChannel();
             RandomAccessFile raf2 = new RandomAccessFile("D:\\Java\\NIO\\data\\2.txt", "rw");
             FileChannel channel2 = raf2.getChannel()) {

            //创建缓冲区
            ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);

            //分散读取
            ByteBuffer[] byteBuffers = {byteBuffer1,byteBuffer2};
            channel1.read(byteBuffers);

            for (ByteBuffer byteBuffer : byteBuffers) {
                byteBuffer.flip();
            }

            //聚集写入
            channel2.write(byteBuffers);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        logger.info("复制结束");

    }
}
