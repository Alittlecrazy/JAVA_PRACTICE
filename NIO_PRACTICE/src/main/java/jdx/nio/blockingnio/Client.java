package jdx.nio.blockingnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        //创建客户端通道并连接到指定ip 端口
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//创建一个缓冲区
        byteBuffer.put("ab".getBytes());//向缓冲区写数据

        byteBuffer.flip();//切换为读模式

        socketChannel.write(byteBuffer);//将缓冲区数据写到通道 即发送数据

        socketChannel.close();
    }
}
