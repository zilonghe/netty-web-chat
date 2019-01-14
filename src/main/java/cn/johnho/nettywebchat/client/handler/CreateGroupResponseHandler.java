package cn.johnho.nettywebchat.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import cn.johnho.nettywebchat.protocol.response.CreateGroupResponsePacket;
import cn.johnho.nettywebchat.util.SessionUtil;

import java.util.List;

/**
 * @author hezilong
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket>
{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception
    {
        System.out.print("群创建成功，id为[" + msg.getGroupId() + "], ");
        String curUserName = SessionUtil.getSession(ctx.channel()).getUserName();
        List<String> userNameList = msg.getUserNameList();
        userNameList.remove(curUserName);

        System.out.println("群里面有：" + userNameList);
    }
}
