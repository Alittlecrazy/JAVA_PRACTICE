package jdx.netty.inboundoutbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long aLong) throws Exception {
        System.out.println("服务器的ip=" + ctx.channel().remoteAddress());
        System.out.println("收到服务器消息=" + aLong);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");

        //ctx.writeAndFlush(Unpooled.copiedBuffer(""));编写 Encoder 是要注意传入的数据类型和处理的数据类型一致 不一致就会跳过encode
        ctx.writeAndFlush(1111111L);//发送的是long数据
    }
}
