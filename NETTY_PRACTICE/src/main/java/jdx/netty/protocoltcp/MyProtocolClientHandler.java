package jdx.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyProtocolClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0;i < 10;i++){
            String msg = "Hello Server" + i;
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(msg.length());
            messageProtocol.setContent(msg.getBytes());
            ctx.writeAndFlush(messageProtocol);
        }
    }
}
