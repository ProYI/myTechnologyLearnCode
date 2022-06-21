package org.example.netty.demo_tcp_$.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @param <SID> SessionId的类型
 *
 */
@Slf4j
@Getter
public abstract class AbstractNettyServer<SID> {

    protected final int port;
    protected SessionManager<SID> sessionManager = initSessionManager();
    protected NioEventLoopGroup bossGroup;
    protected NioEventLoopGroup workGroup;
    protected EventExecutorGroup businessGroup;

    protected AbstractNettyServer(int port) {
        this.port = port;
    }

    /**
     * 客户端连接通道Channel初始化处理
     *
     * @param ch 通道
     */
    protected abstract void initChannel(NioSocketChannel ch);

    public void start() {
        // 执行三个线程池的初始化工作
        initBossGroup();
        initWorkGroup();
        initBusinessGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        AbstractNettyServer.this.initChannel(ch);

                        // 注册每个客户端的连接
                        ch.pipeline().addFirst(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                sessionManager.add(ctx.channel());
                                onClientConnected(ctx.channel());
                                super.channelActive(ctx);
                            }
                        });
                    }
                });
        // Netty Server相关属性配置
        channel(serverBootstrap);
        option(serverBootstrap);
        childOption(serverBootstrap);

        // serving request handler
        servingRequestHandler(serverBootstrap);

        if (workGroup != null) {
            serverBootstrap.group(bossGroup, workGroup);
        } else {
            serverBootstrap.group(bossGroup);
        }

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(getPort()).sync();
            channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
                onStartup();
            });

            Channel serverChannel = channelFuture.channel();
            // wait until server socket close
            serverChannel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException("Netty server start failed.", e);
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully();
        }
        if (businessGroup != null) {
            businessGroup.shutdownGracefully();
        }
    }

    public ChannelFuture write(Object message, Channel channel) {
        return channel.write(message);
    }

    public ChannelGroupFuture write(Object message, SID sessionId) {
        return sessionManager.write(message, sessionId);
    }

    public ChannelGroupFuture write(Object message, Collection<SID> sessionIds) {
        return sessionManager.write(message,
                channel -> sessionIds.contains(sessionManager.findSessionIdByChannel(channel))
        );
    }

    public ChannelFuture writeAndFlush(Object message, Channel channel) {
        return channel.writeAndFlush(message);
    }

    public ChannelGroupFuture writeAndFlush(Object message, SID sessionId) {
        return sessionManager.writeAndFlush(message, sessionId);
    }

    public ChannelGroupFuture writeAndFlush(Object message, Collection<SID> sessionIds) {
        return sessionManager.writeAndFlush(message,
                channel -> sessionIds.contains(sessionManager.findSessionIdByChannel(channel))
        );
    }

    /**
     * 绑定Channel和会话标识ID的关系
     *
     * @param channel   channel通道
     * @param sessionId 会话ID
     */
    public void bindSession(Channel channel, SID sessionId) {
        getSessionManager().bindSession(channel, sessionId);
    }

    /**
     * 解绑Channel和会话标识ID的关系
     *
     * @param sessionId 会话ID
     */
    public Channel unbindSession(SID sessionId) {
        return getSessionManager().unbindSession(sessionId);
    }

    /**
     * 关闭指定会话ID对应的Channel
     *
     * @param sessionId 会话ID
     *
     * @return 如果会话绑定了Channel，那么则返回对应的CloseFuture, 否则返回null
     */
    public ChannelFuture closeChannel(SID sessionId) {
        Channel channel = getSessionManager().findChannelBySessionId(sessionId);
        if (channel != null) {
            return channel.close();
        }
        return null;
    }

    /**
     * 当有客户端连接时
     *
     * @param channel 通道
     */
    protected void onClientConnected(Channel channel) {

    }

    /**
     * 当有客户端断开连接时
     *
     * @param channel   通道
     * @param sessionId 会话ID, 当子类维护了通道与业务ID的会话时, 传入绑定时的会话ID, 否则为null
     */
    protected void onClientDisconnected(Channel channel, SID sessionId) {

    }

    /**
     * Netty启动完成时的操作
     */
    protected void onStartup() {
        log.info("Netty server start successfully on port:{}", getPort());
        // implementation by subclass
    }

    protected void channel(ServerBootstrap serverBootstrap) {
        serverBootstrap.channel(NioServerSocketChannel.class);
    }

    protected void option(ServerBootstrap serverBootstrap) {
        serverBootstrap.option(NioChannelOption.SO_BACKLOG, 1024);
    }

    protected void childOption(ServerBootstrap serverBootstrap) {
        serverBootstrap.childOption(NioChannelOption.TCP_NODELAY, true);
    }

    protected void servingRequestHandler(ServerBootstrap serverBootstrap) {
        // implementation by subclass
    }

    /**
     * 初始化客户端连接/断开监听线程池
     */
    protected void initBossGroup() {
        bossGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("boss"));
    }

    /**
     * 初始化读写I/O监听线程池
     */
    protected void initWorkGroup() {
        workGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));
    }

    /**
     * 初始化业务线程池
     */
    protected void initBusinessGroup() {
        businessGroup = new UnorderedThreadPoolEventExecutor(16, new DefaultThreadFactory("business"));
    }

    /**
     * 初始化Session管理器
     */
    protected SessionManager<SID> initSessionManager() {
        return new SessionManager<>(this);
    }

}
