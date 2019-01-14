package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.session.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.JOIN_GROUP_RESPONSE;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupResponsePacket extends BasePacket
{
    private Session joinMember;
    private String groupId;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand()
    {
        return JOIN_GROUP_RESPONSE;
    }
}
