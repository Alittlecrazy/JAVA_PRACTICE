package jdx.nio.nonblockingnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 *    java.nio.channels.Channel 接口：
 * 		|--SelectableChannel
 * 			|--SocketChannel
 * 			|--ServerSocketChannel
 * 			|--DatagramChannel
 *
 * 			|--Pipe.SinkChannel
 * 			|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9899));

        socketChannel.configureBlocking(false);

        Scanner scanner = new Scanner(System.in);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (scanner.hasNext()) {
            String next = scanner.next();
            byteBuffer.put((new Date() + "\n" + next).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.close();

    }
}
