package jdx.nio.filechannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 利用通道完成文件复制 非直接缓冲区·
 */
public class Demo1 {

    public static final Logger logger = LoggerFactory.getLogger(Demo1.class);

    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("D:\\Java\\NIO\\data\\1.txt");
            fos = new FileOutputStream("D:\\Java\\NIO\\data\\2.txt");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (inChannel.read(byteBuffer) != -1) {//通道读不到数据会返回-1
                byteBuffer.flip();//读写反转
                outChannel.write(byteBuffer);

                byteBuffer.clear();
            }

        } catch (Exception e) {
            logger.error("复制文件失败",e);
        } finally {
            if (outChannel != null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        logger.info("文件复制完成");

    }
}
