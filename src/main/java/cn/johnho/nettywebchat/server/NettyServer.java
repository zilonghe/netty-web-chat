package cn.johnho.nettywebchat.server;

import cn.johnho.nettywebchat.codec.PacketCodecHandler;
import cn.johnho.nettywebchat.codec.Spliter;
import cn.johnho.nettywebchat.server.handler.AuthHandler;
import cn.johnho.nettywebchat.server.handler.IMHandler;
import cn.johnho.nettywebchat.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

@Component
public class NettyServer
{
    private static final Logger log = Logger.getLogger(NettyServer.class);
    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture run(InetSocketAddress address)
    {
        ChannelFuture f = null;
        try
        {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>()
                    {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel)
                        {
                            nioSocketChannel.pipeline().addLast(new Spliter());
                            nioSocketChannel.pipeline().addLast(PacketCodecHandler.INSTANCE);
                            nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                            nioSocketChannel.pipeline().addLast(AuthHandler.INSTANCE);
                            nioSocketChannel.pipeline().addLast(IMHandler.INSTANCE);
                        }
                    });
            f = serverBootstrap.bind(address).syncUninterruptibly();
            channel = f.channel();
        }
        catch (Exception e)
        {
            log.error("Netty start error:", e);
        }
        finally
        {
            if (f != null && f.isSuccess())
            {
                log.info("Netty server listening " + address.getHostName() + " on port " + address.getPort() + " and ready for connections...");
            }
            else
            {
                log.error("Netty server start up Error!");
            }
        }
        return f;
    }

    public void destroy()
    {
        log.info("Shutdown Netty Server...");
        if (channel != null)
        {
            channel.close();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        log.info("Shutdown Netty Server Success!");
    }
}
