package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.LOGIN_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends BasePacket
{
    private String userName;

    private String userId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand()
    {
        return LOGIN_RESPONSE;
    }
}
