package cn.johnho.nettywebchat.client.console;

import cn.johnho.nettywebchat.protocol.BasePacket;
import io.netty.channel.Channel;
import cn.johnho.nettywebchat.protocol.request.MessageRequestPacket;

import java.util.Scanner;

/**
 * @author hezilong
 */
public class SendToUserConsoleCommand implements ConsoleCommand
{
    @Override
    public void exec(Scanner scanner, Channel channel)
    {

    }

    @Override
    public void exec(BasePacket model, Channel channel)
    {

    }
}
