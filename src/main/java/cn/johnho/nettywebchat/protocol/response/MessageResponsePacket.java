package cn.johnho.nettywebchat.protocol.response;

import cn.johnho.nettywebchat.protocol.BasePacket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static cn.johnho.nettywebchat.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author hezilong
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MessageResponsePacket extends BasePacket
{
    private boolean success;
    private String message;
    private String fromUserId;
    private String fromUserName;
    private String reason;

    public MessageResponsePacket(String message, String fromUserId, String fromUserName)
    {
        this.message = message;
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
    }

    public MessageResponsePacket(boolean success, String reason)
    {
        this.success = success;
        this.reason = reason;
    }

    @Override
    public Byte getCommand()
    {
        return MESSAGE_RESPONSE;
    }
}
