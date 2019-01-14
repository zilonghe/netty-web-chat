package cn.johnho.nettywebchat.protocol.request;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupRequestPacket extends BasePacket
{
    private String groupId;

    @Override
    public Byte getCommand()
    {
        return JOIN_GROUP_REQUEST;
    }
}
