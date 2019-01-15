package cn.johnho.nettywebchat.client.console;

import cn.johnho.nettywebchat.protocol.BasePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author hezilong
 */
public interface ConsoleCommand<T extends BasePacket>
{
    default void exec(Scanner scanner, Channel channel)
    {
    }

    default void exec(T model, Channel channel)
    {
        channel.writeAndFlush(model);
    }
}
