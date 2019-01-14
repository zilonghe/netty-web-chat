package cn.johnho.nettywebchat;

import cn.johnho.nettywebchat.server.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class NettyWebChatApplication implements CommandLineRunner
{

    private final NettyServer socketServer;
    @Value("${n.port}")
    private int port;
    @Value("${n.url}")
    private String url;

    @Autowired
    public NettyWebChatApplication(NettyServer socketServer)
    {
        this.socketServer = socketServer;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(NettyWebChatApplication.class, args);
    }

    @Override
    public void run(String... strings)
    {
        InetSocketAddress address = new InetSocketAddress(url, port);
        ChannelFuture future = socketServer.run(address);
        Runtime.getRuntime().addShutdownHook(new Thread(socketServer::destroy));
        future.channel().closeFuture().syncUninterruptibly();
    }
}

