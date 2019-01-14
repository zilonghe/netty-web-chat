package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.session.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.johnho.nettywebchat.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuitGroupResponsePacket extends BasePacket
{
    private boolean success;
    private String groupId;
    private String reason;
    private Session quitMember;

    @Override
    public Byte getCommand()
    {
        return QUIT_GROUP_RESPONSE;
    }
}
