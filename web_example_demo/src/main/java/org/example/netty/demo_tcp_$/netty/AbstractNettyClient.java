package org.example.netty.demo_tcp_$.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class AbstractNettyClient {

    protected NioEventLoopGroup group;
    protected Channel channel;

    protected final String host;
    protected final int port;

    public AbstractNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 客户端连接通道Channel初始化处理
     *
     * @param ch 通道
     */
    protected abstract void initChannel(NioSocketChannel ch);

    public void connect() {
        // 执行线程池的初始化工作
        initGroup();

        Bootstrap bootstrap = new Bootstrap()
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        AbstractNettyClient.this.initChannel(ch);
                    }
                });

        // Netty Client相关属性配置
        channel(bootstrap);
        option(bootstrap);

        bootstrap.group(group);

        try {
            ChannelFuture channelFuture = bootstrap.connect(getHost(), getPort()).sync();
            channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
                channel = channelFuture1.channel();
                onConnect();
            });

            // wait until client socket close
            ChannelFuture sync = channelFuture.channel().closeFuture().sync();
            sync.addListener((ChannelFutureListener) future -> {
                onDisconnect();
            });
        } catch (Exception e) {
            onConnectFailed(e);
        } finally {
            close();
        }
    }

    public void close() {
        if (channel != null) {
            channel.close();
        }
        if (group != null) {
            group.shutdownGracefully();
        }
    }

    public ChannelFuture write(Object msg) {
        requireChannelEnable(channel);
        return channel.write(msg);
    }

    public ChannelFuture writeAndFlush(Object message) {
        requireChannelEnable(channel);
        return channel.writeAndFlush(message);
    }

    /**
     * 当连接失败时的处理
     *
     * @param e 连接异常
     */
    protected void onConnectFailed(Exception e) {
        log.error("Netty client connect failed to {}:{}", getHost(), getPort(), e);
        // implementation by subclass
    }

    /**
     * 当连接成功时的处理
     */
    protected void onConnect() {
        log.info("Netty client connect successfully to {}:{}", getHost(), getPort());
        // implementation by subclass
    }

    /**
     * 当连接断开时的处理
     */
    protected void onDisconnect() {
        log.info("Netty client disconnect.");
        // implementation by subclass
    }

    protected void channel(Bootstrap bootstrap) {
        bootstrap.channel(NioSocketChannel.class);
    }

    protected void option(Bootstrap bootstrap) {
        // 连接5秒超时
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5 * 1000);
    }

    /**
     * 初始化客户端连接/断开监听线程池
     */
    protected void initGroup() {
        group = new NioEventLoopGroup();
    }

    private void requireChannelEnable(Channel channel) {
        if (!channel.isActive() || !channel.isWritable()) {
            throw new IllegalStateException("channel is non-writable now, message dropped.");
        }
    }

}
