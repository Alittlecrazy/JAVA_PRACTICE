package jdx.nio.filechannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 通道之间的传输(直接缓冲区)
 */
public class Demo3 {
    public static final Logger logger = LoggerFactory.getLogger(Demo3.class);

    public static void main(String[] args) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\Java\\NIO\\data\\1.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\Java\\NIO\\data\\2.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //两种方式
        inChannel.transferTo(0,inChannel.size(),outChannel);
        //outChannel.transferFrom(inChannel,0,inChannel.size());

        inChannel.close();
        outChannel.close();

        logger.info("复制结束");
    }
}
