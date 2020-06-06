package com.york.heartbeatClient;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * @author york
 * @create 2020-06-06 17:17
 **/
public class CustomerHandleInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new IdleStateHandler(0,10,0))
                .addLast();
    }
}
