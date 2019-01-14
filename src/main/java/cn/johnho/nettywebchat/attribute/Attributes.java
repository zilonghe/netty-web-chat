package cn.johnho.nettywebchat.attribute;

import cn.johnho.nettywebchat.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author hezilong
 */
public interface Attributes
{
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
