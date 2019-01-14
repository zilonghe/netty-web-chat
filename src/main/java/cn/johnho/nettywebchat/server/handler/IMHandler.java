package cn.johnho.nettywebchat.server.handler;

import cn.johnho.nettywebchat.protocol.BasePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static cn.johnho.nettywebchat.protocol.command.Command.CREATE_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.GROUP_MESSAGE_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.JOIN_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LIST_GROUP_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGIN_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.LOGOUT_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.MESSAGE_REQUEST;
import static cn.johnho.nettywebchat.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * @author hezilong
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<BasePacket>
{
    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends BasePacket>> handlerMap;

    private IMHandler()
    {
        handlerMap = new HashMap<>();

        handlerMap.put(LOGIN_REQUEST, LoginRequestHandler.INSTANCE);
        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_REQUEST, ListGroupRequestHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BasePacket msg) throws Exception
    {
        handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
