package org.example.netty.demo_tcp_$.netty;

import com.google.common.collect.HashBiMap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.ChannelMatchers;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * ChannelGroup扩展, 支持自定义会话ID
 */
public class SessionManager<SID> extends DefaultChannelGroup {

    private final AbstractNettyServer<SID> server;
    private final HashBiMap<SID, Channel> sessionChannelMap = HashBiMap.create();

    public SessionManager(AbstractNettyServer<SID> server) {
        super(GlobalEventExecutor.INSTANCE);
        this.server = server;
    }

    @Override
    public boolean remove(Object o) {
        Channel c = null;
        if (o instanceof ChannelId) {
            c = find((ChannelId) o);
        } else if (o instanceof Channel) {
            c = (Channel) o;
        }
        if (c == null) {
            return super.remove(o);
        }
        // 当客户端断开连接
        server.onClientDisconnected(c, findSessionIdByChannel(c));
        sessionChannelMap.inverse().remove(c);
        return super.remove(c);
    }

    @Override
    public void clear() {
        super.clear();
        sessionChannelMap.clear();
    }

    /**
     * 绑定Channel和会话标识ID的关系
     *
     * @param channel   channel通道
     * @param sessionId 会话ID
     */
    public void bindSession(Channel channel, SID sessionId) {
        sessionChannelMap.forcePut(sessionId, channel);
    }

    /**
     * 解绑Channel和会话标识ID的关系
     *
     * @param sessionId 会话ID
     */
    public Channel unbindSession(SID sessionId) {
        return sessionChannelMap.remove(sessionId);
    }

    /**
     * 根据会话ID查找Channel
     *
     * @param sessionId 会话ID
     *
     * @return Channel
     */
    public Channel findChannelBySessionId(SID sessionId) {
        return sessionChannelMap.get(sessionId);
    }

    /**
     * 根据Channel查找会话ID
     *
     * @param channel 通道
     *
     * @return sessionId
     */
    public SID findSessionIdByChannel(Channel channel) {
        return sessionChannelMap.inverse().get(channel);
    }

    /**
     * 判断会话ID是否存在
     *
     * @param sessionId 会话ID
     *
     * @return 存在返回true, 否则返回false
     */
    public boolean existsSession(SID sessionId) {
        return sessionChannelMap.containsKey(sessionId);
    }

    /**
     * 往指定会话ID所绑定的Channel中写数据
     *
     * @param message   数据
     * @param sessionId 会话ID
     *
     * @return channelGroupFuture
     */
    public ChannelGroupFuture write(Object message, SID sessionId) {
        return super.write(message, ChannelMatchers.is(findChannelBySessionId(sessionId)));
    }

    /**
     * 往指定会话ID所绑定的Channel中写数据并刷写缓冲区
     *
     * @param sessionId 会话ID
     * @param message   数据
     *
     * @return channelGroupFuture
     */
    public ChannelGroupFuture writeAndFlush(Object message, SID sessionId) {
        return super.writeAndFlush(message, ChannelMatchers.is(findChannelBySessionId(sessionId)));
    }

}
