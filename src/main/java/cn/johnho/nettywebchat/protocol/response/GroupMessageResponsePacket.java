package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.session.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageResponsePacket extends BasePacket
{
    private String message;
    private Session fromUser;
    private String groupId;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand()
    {
        return GROUP_MESSAGE_RESPONSE;
    }
}
