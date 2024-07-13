package io.github.junjiaye.yejj.cache.core;

import io.github.junjiaye.yejj.cache.YeJJPlugin;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.stereotype.Component;

/**
 * @program: yejjcache
 * @ClassName: YeJJCacheServer
 * @description:
 * @author: yejj
 * @create: 2024-06-16 10:48
 */
@Component
public class YeJJCacheServer implements YeJJPlugin {

    int port = 6379;

    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    Channel channel;
    @Override
    public void init() {
        bossGroup = new NioEventLoopGroup(1,    new DefaultThreadFactory("redis-boss"));
        workerGroup = new NioEventLoopGroup(16, new DefaultThreadFactory("redis-work"));
    }

    @Override
    public void startup() {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new YeJJCacheDecoder());
                            //ch.pipeline().addLast(new RedisEncoder());
                            ch.pipeline().addLast(new YeJJCacheHandler());
                        }
                    });
            channel = b.bind(port).sync().channel();
            System.out.println("开启netty redis服务器，端口为 " + port);
            channel.closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void shutdown() {
        //从里往外停
        if (this.channel != null){
            this.channel.close();
            this.channel = null;
        }

        //bossgroup停止之后，停止接收请求
        if (this.bossGroup != null){
            this.bossGroup.shutdownGracefully();
            this.bossGroup = null;
        }
        //停止所有请求的处理
        if (this.workerGroup != null){
            this.workerGroup.shutdownGracefully();
            this.workerGroup = null;
        }

    }
}
