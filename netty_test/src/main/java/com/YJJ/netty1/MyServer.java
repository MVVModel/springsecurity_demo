package com.YJJ.netty1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MyServer {
    public static void main(String[] args)throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup woke = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, woke).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new MyInitializer());
            ChannelFuture future = serverBootstrap.bind(8089).sync();
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            woke.shutdownGracefully();
        }
    }
}
