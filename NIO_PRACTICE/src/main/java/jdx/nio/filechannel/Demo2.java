package jdx.nio.filechannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用直接缓冲区复制文件
 */
public class Demo2 {

    public static final Logger logger = LoggerFactory.getLogger(Demo2.class);

    public static void main(String[] args) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\Java\\NIO\\data\\1.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\Java\\NIO\\data\\2.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据读写操作
        byte[] bytes = new byte[inMappedBuf.limit()];

        inMappedBuf.get(bytes);
        outMapperBuf.put(bytes);

        inChannel.close();
        outChannel.close();

        logger.info("复制结束");

    }
}
