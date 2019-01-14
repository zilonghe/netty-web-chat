package cn.johnho.nettywebchat.codec;

import cn.johnho.nettywebchat.protocol.BasePacket;
import cn.johnho.nettywebchat.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<BasePacket>
{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BasePacket basePacket, ByteBuf byteBuf) throws Exception
    {
        PacketCodeC.INSTANCE.encode(byteBuf, basePacket);
    }
}
