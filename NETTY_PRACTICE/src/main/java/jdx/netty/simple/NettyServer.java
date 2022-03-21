package jdx.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty服务端
 */
public class NettyServer {

    public static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    public static void main(String[] args) throws InterruptedException {
        //1.服务端需要创建两个线程组 bossGroup 和 workerGroup
        //2.bossGroup只处理连接请求 真正的客户端业务处理会交给workerGroup完成
        //3.两个线程组都是无线循环
        //4.bossGroup 和 workerGroup 含有的子线程(NioEventLoop)的个数 默认实际 cpu核数 * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务端的启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel 作为服务器通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列 连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态 可以通过应用层实现了解服务端或客户端状态，而决定是否继续维持该Socket。
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象(匿名对象)
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });


            logger.info("...服务器启动完成...");

            //启动服务器并绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(6669).sync();

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }





    }

}
