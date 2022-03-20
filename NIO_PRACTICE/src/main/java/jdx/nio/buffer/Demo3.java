package jdx.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 分配直接缓冲区
 */
public class Demo3 {
    public static void main(String[] args) {
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        System.out.println(buf.isDirect());
    }
}
