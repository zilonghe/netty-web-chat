package cn.johnho.nettywebchat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User
{
    private String account;
    private String pwd;

    public User(String account, String pwd)
    {
        this.account = account;
        this.pwd = pwd;
    }
}
