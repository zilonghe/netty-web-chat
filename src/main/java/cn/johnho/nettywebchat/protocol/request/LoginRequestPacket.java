package cn.johnho.nettywebchat.protocol.request;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.LOGIN_REQUEST;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class LoginRequestPacket extends BasePacket
{

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand()
    {
        return LOGIN_REQUEST;
    }
}
