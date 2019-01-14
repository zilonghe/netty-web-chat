package cn.johnho.nettywebchat.protocol.request;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.LIST_GROUP_REQUEST;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupRequestPacket extends BasePacket
{
    private String groupId;

    @Override
    public Byte getCommand()
    {
        return LIST_GROUP_REQUEST;
    }
}
