package jdx.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyProtocolServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        int len = messageProtocol.getLen();
        byte[] content = messageProtocol.getContent();

        System.out.println("服务器收到消息长度" + len);
        System.out.println("服务器收到消息" + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器收到消息包数量" + (++count));

    }
}
