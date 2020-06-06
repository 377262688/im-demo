package com.york.heartbeatClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.channels.SocketChannel;

/**
 * @author york
 * @create 2020-06-06 17:10
 **/
@Component
public class HeartbeatClient {

    private Logger logger = LoggerFactory.getLogger(HeartbeatClient.class);

    private EventLoopGroup group = new NioEventLoopGroup();

    private SocketChannel channel;
    @Value("${netty.server.host}")
    private String host;
    @Value("${netty.server.port}")
    private Integer port;

    @PostConstruct
    public void start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new CustomerHandleInitializer());
        ChannelFuture future = bootstrap.connect(host, port).sync();
        if (future.isSuccess()) {
            logger.info("启动 Netty 成功");
        }
        channel = (SocketChannel) future.channel();
    }
}
