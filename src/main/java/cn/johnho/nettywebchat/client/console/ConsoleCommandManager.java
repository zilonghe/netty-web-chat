package cn.johnho.nettywebchat.client.console;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.util.SessionUtil;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static cn.johnho.nettywebchat.protocol.command.Command.CREATE_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.GROUP_MESSAGE_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LIST_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGIN_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGOUT_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.MESSAGE_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * @author hezilong
 */
@Component
public class ConsoleCommandManager implements ConsoleCommand
{
    private static Map<Byte, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager()
    {
        consoleCommandMap = new HashMap<>();

        consoleCommandMap.put(MESSAGE_REQUEST, new SendToUserConsoleCommand());

        consoleCommandMap.put(LOGIN_REQUEST, new LoginConsoleCommand());
        consoleCommandMap.put(LOGOUT_REQUEST, new LogoutConsoleCommand());

        consoleCommandMap.put(CREATE_GROUP_REQUEST, new CreateGroupConsoleCommand());
        consoleCommandMap.put(QUIT_GROUP_REQUEST, new QuitGroupConsoleCommand());
        consoleCommandMap.put(LIST_GROUP_REQUEST, new ListGroupConsoleCommand());
        consoleCommandMap.put(GROUP_MESSAGE_REQUEST, new GroupMessageConsoleCommand());
    }

    public static Map<Byte, ConsoleCommand> getConsoleCommandMap()
    {
        return consoleCommandMap;
    }

    @Override
    public void exec(BasePacket model, Channel channel)
    {
        if (!SessionUtil.hasLogin(channel))
        {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(model.getCommand());

        if (consoleCommand != null)
        {
            consoleCommand.exec(model, channel);
        }
        else
        {
            System.err.println("无法识别[" + model.getCommand() + "]指令");
        }
    }
}
