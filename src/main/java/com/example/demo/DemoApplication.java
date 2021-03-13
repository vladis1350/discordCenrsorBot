package com.example.demo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DemoApplication {

    public static void main(String[] args) {
        JDA api = null;
        EncryptService encryptService = new EncryptService();
        try {
            String token = "9K2LlBdRdilrRi+O53a2s9yIdcg47ZYlJC73+HJFP3z/k1GB8K1pyBt5gH63ot1Z6ReeDf0wX9adozatFQxi+ZlKDWp+HTts";
            token = encryptService.decryptPass(token);
            api = JDABuilder.createDefault(token).build();

            api.addEventListener(new MyEventListener());
            System.out.println("Бот успешно подключен.");
        } catch (LoginException e) {
            System.err.println("Ошибка при подключении бота к Discord: " + e.getMessage());
        }
    }

}
