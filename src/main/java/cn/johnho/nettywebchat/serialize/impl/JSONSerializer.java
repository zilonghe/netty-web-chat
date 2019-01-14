package cn.johnho.nettywebchat.serialize.impl;

import cn.johnho.nettywebchat.serialize.Serializer;
import cn.johnho.nettywebchat.serialize.SerializerAlgorithm;
import com.alibaba.fastjson.JSON;

public class JSONSerializer implements Serializer
{
    @Override
    public byte getSerializerAlgorithm()
    {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj)
    {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes)
    {
        return JSON.parseObject(bytes, clazz);
    }
}
