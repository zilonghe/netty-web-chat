package cn.johnho.nettywebchat.client.console;

import cn.johnho.nettywebchat.protocol.BasePacket;
import io.netty.channel.Channel;
import cn.johnho.nettywebchat.protocol.request.QuitGroupRequestPacket;

import java.util.Scanner;

/**
 * @author hezilong
 */
public class QuitGroupConsoleCommand implements ConsoleCommand
{
    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }

    @Override
    public void exec(BasePacket model, Channel channel)
    {

    }
}
