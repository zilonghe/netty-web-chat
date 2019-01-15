package cn.johnho.nettywebchat.client.console;

import cn.johnho.nettywebchat.protocol.BasePacket;
import io.netty.channel.Channel;
import cn.johnho.nettywebchat.protocol.request.JoinGroupRequestPacket;

import java.util.Scanner;

/**
 * @author hezilong
 */
public class JoinGroupConsoleCommand implements ConsoleCommand
{
    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入groupId，加入群聊: ");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }

    @Override
    public void exec(BasePacket model, Channel channel)
    {

    }
}
