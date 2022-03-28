package jdx.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {

    public static void main(String[] args) {
        /**
         * 创建一个ByteBuf
         * 说明
         * 1.创建对象 这个对象包含一个数组arr,是一个byte数组 byte[]
         * 2.在Netty的buf中 不需要使用flip反转  底层维护了 readerIndex 和 writerIndex
         *
         * 3.通过 readerIndex writerIndex capacity 将buf分成3个区域
         * 0---readerIndex 已经读取的区域
         * readerIndex---writerIndex  可读区域
         * writerIndex---capacity  可写区域
         */

        ByteBuf byteBuf = Unpooled.buffer(10);//这是一个byte[10]

        for (int i = 0;i < 10;i++) {
            byteBuf.writeByte(i);
        }

        System.out.println("capacity=" + byteBuf.capacity());
        System.out.println("readerIndex" + byteBuf.readerIndex());
        System.out.println("writerIndex" + byteBuf.writerIndex());

        System.out.println("======================================");

        for(int i = 0; i < byteBuf.capacity(); i++) {
            System.out.println(byteBuf.readByte());
        }

        System.out.println("capacity=" + byteBuf.capacity());
        System.out.println("readerIndex" + byteBuf.readerIndex());
        System.out.println("writerIndex" + byteBuf.writerIndex());


    }
}
