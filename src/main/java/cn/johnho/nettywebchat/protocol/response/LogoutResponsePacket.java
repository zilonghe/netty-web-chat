package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutResponsePacket extends BasePacket
{
    private boolean success;

    @Override
    public Byte getCommand()
    {
        return LOGOUT_RESPONSE;
    }
}
