package cn.johnho.nettywebchat.codec;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author hezilong
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, BasePacket>
{

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    private PacketCodecHandler()
    {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket msg, List<Object> out) throws Exception
    {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf, msg);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception
    {
        out.add(PacketCodeC.INSTANCE.decode(msg));
    }
}
