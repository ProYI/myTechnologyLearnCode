package org.example.netty.demo_tcp_err.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {
    private int port;

    public TimeServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        /**
         *  线程组， 实际是Reactor线程组
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 工作线程
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            /**
             * ServerBootstrap用于NIO服务端的辅助启动类，目的：降低服务端开发复杂度
             */
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    // NioServerSocketChannel对应 JDK NIO类库中的ServerSocketChannel类
                    .channel(NioServerSocketChannel.class)
                    // 设置TCP参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 绑定I/O事件处理类 例如记录日志、对消息进行编解码
                    .childHandler(new ChildChannelHandle())
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            /**
             * 调用bind方法监听端口，调用同步阻塞方法sync等待绑定操作完成
             * 绑定完成后返回ChannelFuture,主要用于异步操作的 通知回调
             */
            ChannelFuture f = b.bind(port).sync();
            /**
             * 阻塞方法，等待服务端链路关闭之后 main函数才能退出
             */
            f.channel().closeFuture().sync();
        } finally {
            /**
             * NIO线程组优雅退出，释放资源
             */
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandle extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 9000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new TimeServer(port).run();
    }

}
