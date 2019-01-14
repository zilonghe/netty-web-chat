package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static cn.johnho.nettywebchat.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupResponsePacket extends BasePacket
{
    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand()
    {
        return CREATE_GROUP_RESPONSE;
    }
}
