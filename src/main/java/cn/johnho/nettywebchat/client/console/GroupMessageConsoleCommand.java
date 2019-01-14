package cn.johnho.nettywebchat.client.console;

import cn.johnho.nettywebchat.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author hezilong
 */
public class GroupMessageConsoleCommand implements ConsoleCommand
{
    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket();
        System.out.print("发送消息至群组(格式：群聊id 消息)：");
        String groupId = scanner.next();
        String message = scanner.next();
        requestPacket.setGroupId(groupId);
        requestPacket.setMessage(message);
        channel.writeAndFlush(requestPacket);
    }
}
