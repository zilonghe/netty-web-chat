package cn.johnho.nettywebchat.client.handler;

import cn.johnho.nettywebchat.protocol.response.JoinGroupResponsePacket;
import cn.johnho.nettywebchat.session.Session;
import cn.johnho.nettywebchat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hezilong
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception
    {
        if (msg.isSuccess())
        {
            Session selfSession = SessionUtil.getSession(ctx.channel());
            if (selfSession.equals(msg.getJoinMember()))
            {
                System.out.println("加入群[" + msg.getGroupId() + "]成功");
            }
            else
            {
                System.out.println(msg.getJoinMember() + "加入群[" + msg.getGroupId() + "]");
            }
        }
        else
        {
            System.out.println("加入群[" + msg.getGroupId() + "]失败，原因：[" + msg.getReason() + "]");
        }
    }
}
