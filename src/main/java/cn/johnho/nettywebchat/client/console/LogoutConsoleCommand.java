package cn.johnho.nettywebchat.client.console;

import io.netty.channel.Channel;
import cn.johnho.nettywebchat.protocol.request.LogoutRequestPacket;

import java.util.Scanner;

/**
 * @author hezilong
 */
public class LogoutConsoleCommand implements ConsoleCommand
{
    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        channel.writeAndFlush(new LogoutRequestPacket());
    }
}
