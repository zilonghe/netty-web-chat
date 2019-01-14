package cn.johnho.nettywebchat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import cn.johnho.nettywebchat.protocol.response.QuitGroupResponsePacket;
import cn.johnho.nettywebchat.session.Session;
import cn.johnho.nettywebchat.util.SessionUtil;

/**
 * @author hezilong
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception
    {
        if (msg.isSuccess())
        {
            Session selfSession = SessionUtil.getSession(ctx.channel());
            if (selfSession.equals(msg.getQuitMember()))
            {
                System.out.println("退出群聊[" + msg.getGroupId() + "]成功");
            }
            else
            {
                System.out.println(msg.getQuitMember() + "退出群聊[" + msg.getGroupId() + "]");
            }

        }
        else
        {
            System.out.println("退出群聊[" + msg.getGroupId() + "]失败，原因是：[" + msg.getReason() + "]");
        }
    }
}
