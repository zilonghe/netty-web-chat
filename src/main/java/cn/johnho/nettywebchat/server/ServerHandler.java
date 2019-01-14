package cn.johnho.nettywebchat.server;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.protocol.PacketCodeC;
import cn.johnho.nettywebchat.protocol.request.LoginRequestPacket;
import cn.johnho.nettywebchat.protocol.request.MessageRequestPacket;
import cn.johnho.nettywebchat.protocol.response.LoginResponsePacket;
import cn.johnho.nettywebchat.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        // 解码
        BasePacket packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket)
        {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginRequestPacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket))
            {
                loginResponsePacket.setSuccess(true);
            }
            else
            {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
            }

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
        else if (packet instanceof MessageRequestPacket)
        {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务器端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.writeAndFlush(byteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket)
    {
        return true;
    }
}
