package cn.johnho.nettywebchat.client.console;

import cn.johnho.nettywebchat.protocol.BasePacket;
import io.netty.channel.Channel;
import cn.johnho.nettywebchat.protocol.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author hezilong
 */
public class CreateGroupConsoleCommand implements ConsoleCommand<CreateGroupRequestPacket>
{

    private static final String USER_ID_SPLITTER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel)
    {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.print("[拉人群聊]输入userId列表，userId用英文逗号隔开:");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITTER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }

    @Override
    public void exec(CreateGroupRequestPacket model, Channel channel)
    {

    }
}
