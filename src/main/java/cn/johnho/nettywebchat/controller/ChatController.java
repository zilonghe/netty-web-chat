package cn.johnho.nettywebchat.controller;

import cn.johnho.nettywebchat.client.console.ConsoleCommandManager;
import cn.johnho.nettywebchat.model.User;
import cn.johnho.nettywebchat.protocol.request.LoginRequestPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController
{
    private final ConsoleCommandManager consoleCommandManager;

    @Autowired
    public ChatController(ConsoleCommandManager consoleCommandManager)
    {
        this.consoleCommandManager = consoleCommandManager;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(User user)
    {
        return true;
    }
}
