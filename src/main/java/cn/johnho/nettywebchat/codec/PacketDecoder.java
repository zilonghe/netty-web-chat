package cn.johnho.nettywebchat.codec;

import cn.johnho.nettywebchat.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author hezilong
 */
public class PacketDecoder extends ByteToMessageDecoder
{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception
    {
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
