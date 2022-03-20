package jdx.nio.blockingnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();//创建一个通道
        serverSocketChannel.bind(new InetSocketAddress(9898));//绑定服务端端口

        SocketChannel socketChannel = serverSocketChannel.accept();//等待客户端调用

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//创建一个1024大小的缓冲区

        int len = 0;
        while ((len = socketChannel.read(byteBuffer)) > 0) {//循环读通道数据到缓冲区
            byteBuffer.flip();//切换缓冲区的读写模式
            System.out.println(new String(byteBuffer.array(),0,len));
            byteBuffer.clear();//清空缓冲区
        }

        //关闭通道
        socketChannel.close();
        serverSocketChannel.close();
    }
}
