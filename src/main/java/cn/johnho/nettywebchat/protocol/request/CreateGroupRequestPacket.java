package cn.johnho.nettywebchat.protocol.request;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static cn.johnho.nettywebchat.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRequestPacket extends BasePacket
{
    private List<String> userIdList;

    @Override
    public Byte getCommand()
    {
        return CREATE_GROUP_REQUEST;
    }
}
