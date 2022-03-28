package jdx.netty.inboundoutbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new MyByteToLongDecoder2());//入站的handler进行解码
        pipeline.addLast(new MyLongToByteEncoder());//出站的handler进行编码

        pipeline.addLast(new MyServerHandler());//自定义handler

    }
}
