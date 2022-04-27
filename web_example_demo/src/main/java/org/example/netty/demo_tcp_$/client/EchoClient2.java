package org.example.netty.demo_tcp_$.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.example.netty.demo_tcp_$.netty.AbstractNettyClient;

public class EchoClient2 extends AbstractNettyClient {
    public EchoClient2(String host, int port) {
        super(host, port);
    }

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new EchoClientHandler());
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient2("127.0.0.1", 8080).connect();
    }
}
