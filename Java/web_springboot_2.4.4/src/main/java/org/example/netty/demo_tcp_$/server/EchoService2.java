package org.example.netty.demo_tcp_$.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.example.netty.demo_tcp_$.netty.AbstractNettyServer;

public class EchoService2 extends AbstractNettyServer {
    public EchoService2() {
        super(8080);
    }

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new EchoServerHandler());
    }

    public static void main(String[] args) throws Exception {

        new EchoService2().start();
    }
}
