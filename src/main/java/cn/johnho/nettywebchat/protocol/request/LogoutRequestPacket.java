package cn.johnho.nettywebchat.protocol.request;

import cn.johnho.nettywebchat.protocol.BasePacket;

import static cn.johnho.nettywebchat.protocol.command.Command.LOGOUT_REQUEST;

public class LogoutRequestPacket extends BasePacket
{
    @Override
    public Byte getCommand()
    {
        return LOGOUT_REQUEST;
    }
}
