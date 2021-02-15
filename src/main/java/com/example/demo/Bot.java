package com.example.demo;

import lombok.Getter;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@Component
@Getter
public class Bot {

    @Value("${bot.token}")
    private String botToken;

//    @Autowired
//    private MyEventListener myEventListener;

    public void regBot(){

    }
}
