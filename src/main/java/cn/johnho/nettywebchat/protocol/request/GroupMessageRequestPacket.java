package cn.johnho.nettywebchat.protocol.request;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageRequestPacket extends BasePacket
{
    private String message;
    private String groupId;

    @Override
    public Byte getCommand()
    {
        return GROUP_MESSAGE_REQUEST;
    }
}
