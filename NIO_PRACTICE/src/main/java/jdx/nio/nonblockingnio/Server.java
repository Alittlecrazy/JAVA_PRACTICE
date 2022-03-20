package jdx.nio.nonblockingnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {

    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9899));
        //创建Selector
        Selector selector = Selector.open();
        //将通道注册到选择器上, 并且指定“监听接收事件”
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            //获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();//获取
            while (selectionKeyIterator.hasNext()) {
                //获取准备“就绪”的是事件
                SelectionKey selectionKey = selectionKeyIterator.next();
                //判断具体是什么事件准备就绪
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);

                }else if (selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    int len = 0;
                    while((len = channel.read(byteBuffer)) > 0 ){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                //取消选择键 SelectionKey
                selectionKeyIterator.remove();
            }
        }

    }
}
