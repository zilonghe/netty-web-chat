package cn.johnho.nettywebchat.protocol;


import cn.johnho.nettywebchat.protocol.request.CreateGroupRequestPacket;
import cn.johnho.nettywebchat.protocol.request.GroupMessageRequestPacket;
import cn.johnho.nettywebchat.protocol.request.JoinGroupRequestPacket;
import cn.johnho.nettywebchat.protocol.request.ListGroupRequestPacket;
import cn.johnho.nettywebchat.protocol.request.LoginRequestPacket;
import cn.johnho.nettywebchat.protocol.request.LogoutRequestPacket;
import cn.johnho.nettywebchat.protocol.request.MessageRequestPacket;
import cn.johnho.nettywebchat.protocol.request.QuitGroupRequestPacket;
import cn.johnho.nettywebchat.protocol.response.CreateGroupResponsePacket;
import cn.johnho.nettywebchat.protocol.response.GroupMessageResponsePacket;
import cn.johnho.nettywebchat.protocol.response.JoinGroupResponsePacket;
import cn.johnho.nettywebchat.protocol.response.ListGroupResponsePacket;
import cn.johnho.nettywebchat.protocol.response.LoginResponsePacket;
import cn.johnho.nettywebchat.protocol.response.LogoutResponsePacket;
import cn.johnho.nettywebchat.protocol.response.MessageResponsePacket;
import cn.johnho.nettywebchat.protocol.response.QuitGroupResponsePacket;
import cn.johnho.nettywebchat.serialize.Serializer;
import cn.johnho.nettywebchat.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static cn.johnho.nettywebchat.protocol.command.Command.CREATE_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.CREATE_GROUP_RESPONSE;
import static cn.johnho.nettywebchat.protocol.command.Command.GROUP_MESSAGE_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.GROUP_MESSAGE_RESPONSE;
import static cn.johnho.nettywebchat.protocol.command.Command.JOIN_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.JOIN_GROUP_RESPONSE;
import static cn.johnho.nettywebchat.protocol.command.Command.LIST_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LIST_GROUP_RESPONSE;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGIN_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGIN_RESPONSE;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGOUT_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGOUT_RESPONSE;
import static cn.johnho.nettywebchat.protocol.command.Command.MESSAGE_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.MESSAGE_RESPONSE;
import static cn.johnho.nettywebchat.protocol.command.Command.QUIT_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * @author hezilong
 */
public class PacketCodeC
{
    public static final PacketCodeC INSTANCE = new PacketCodeC();
    public static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends BasePacket>> PACKET_TYPE_MAP;
    private static final Map<Byte, Serializer> SERIALIZER_MAP;

    static
    {
        PACKET_TYPE_MAP = new HashMap<>();
        PACKET_TYPE_MAP.put(LOGIN_REQUEST, LoginRequestPacket.class);
        PACKET_TYPE_MAP.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        PACKET_TYPE_MAP.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        PACKET_TYPE_MAP.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        PACKET_TYPE_MAP.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        PACKET_TYPE_MAP.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        PACKET_TYPE_MAP.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(LIST_GROUP_REQUEST, ListGroupRequestPacket.class);
        PACKET_TYPE_MAP.put(LIST_GROUP_RESPONSE, ListGroupResponsePacket.class);
        PACKET_TYPE_MAP.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        PACKET_TYPE_MAP.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);

        SERIALIZER_MAP = new HashMap<>();
        JSONSerializer serializer = new JSONSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerAlgorithm(), serializer);
    }

    private PacketCodeC()
    {
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, BasePacket packet)
    {
        // 1.创建ByteBuf对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2.序列化java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3.实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public void encode(ByteBuf byteBuf, BasePacket packet)
    {
        // 序列化java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public BasePacket decode(ByteBuf byteBuf)
    {
        // 跳过magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends BasePacket> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null)
        {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm)
    {
        return SERIALIZER_MAP.get(serializeAlgorithm);
    }

    private Class<? extends BasePacket> getRequestType(byte command)
    {
        return PACKET_TYPE_MAP.get(command);
    }
}
