package cn.johnho.nettywebchat.client.console;

import io.netty.channel.Channel;
import cn.johnho.nettywebchat.protocol.request.LoginRequestPacket;

import java.util.Scanner;

/**
 * @author hezilong
 */
public class LoginConsoleCommand implements ConsoleCommand
{
    private static void waitForLoginResponse()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ignored)
        {
        }
    }

    @Override
    public void exec(Scanner sc, Channel channel)
    {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("输入用户名登录: ");
        loginRequestPacket.setUserName(sc.nextLine());
        // 密码使用默认的
        loginRequestPacket.setPassword("pwd");

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }
}
