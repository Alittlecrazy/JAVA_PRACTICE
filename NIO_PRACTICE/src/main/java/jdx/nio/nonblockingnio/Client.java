package jdx.nio.nonblockingnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

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
