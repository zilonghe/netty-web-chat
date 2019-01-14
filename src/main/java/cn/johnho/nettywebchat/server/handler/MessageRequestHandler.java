package cn.johnho.nettywebchat.server.handler;

import cn.johnho.nettywebchat.protocol.request.MessageRequestPacket;
import cn.johnho.nettywebchat.protocol.response.MessageResponsePacket;
import cn.johnho.nettywebchat.session.Session;
import cn.johnho.nettywebchat.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hezilong
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>
{
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    protected MessageRequestHandler()
    {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception
    {
        Session session = SessionUtil.getSession(ctx.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setSuccess(true);

        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel))
        {
            toUserChannel.writeAndFlush(messageResponsePacket);
            ctx.channel().writeAndFlush(new MessageResponsePacket(true, "发送成功"));
        }
        else
        {
            ctx.channel().writeAndFlush(new MessageResponsePacket(false, "对方不在线"));
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败！");
        }
    }
}
