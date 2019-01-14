package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.session.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static cn.johnho.nettywebchat.protocol.command.Command.LIST_GROUP_RESPONSE;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupResponsePacket extends BasePacket
{
    private String groupId;
    private List<Session> sessionsList;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand()
    {
        return LIST_GROUP_RESPONSE;
    }
}
