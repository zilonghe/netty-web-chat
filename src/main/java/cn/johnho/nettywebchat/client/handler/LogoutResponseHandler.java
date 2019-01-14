package cn.johnho.nettywebchat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import cn.johnho.nettywebchat.protocol.response.LogoutResponsePacket;
import cn.johnho.nettywebchat.util.SessionUtil;

/**
 * @author hezilong
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception
    {
        if (msg.isSuccess())
        {
            SessionUtil.unBindSession(ctx.channel());
            System.out.println("登出成功！");
        }
    }
}
