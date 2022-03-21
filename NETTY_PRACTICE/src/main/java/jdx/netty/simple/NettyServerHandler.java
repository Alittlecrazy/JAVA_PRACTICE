package jdx.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
    自定义handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    /**
     * 读取客户端发送的消息
     * @param ctx 上下文对象,包含管道pipeline 通道channel 地址 等
     * @param msg 客户端发送的数据 默认Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //将msg转成ByteBuf 这里的ByteBuf是Netty提供的  不是NIO的ByteBuffer
        ByteBuf byteBuf = (ByteBuf) msg;
        logger.info("客户端发送的数据为:" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读取完毕后的操作
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("接收到了请求",CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
