package cn.johnho.nettywebchat.controller;

import cn.johnho.nettywebchat.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author h00427535
 */
@RestController
public class HelloController
{
    @RequestMapping("/test")
    public String index()
    {
        return "hello docker";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(User user)
    {
        return true;
    }
}
