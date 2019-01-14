package cn.johnho.nettywebchat.protocol.request;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static cn.johnho.nettywebchat.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MessageRequestPacket extends BasePacket
{
    private String message;
    private String toUserId;

    public MessageRequestPacket(String toUserId, String message)
    {
        this.message = message;
        this.toUserId = toUserId;
    }

    @Override
    public Byte getCommand()
    {
        return MESSAGE_REQUEST;
    }
}
