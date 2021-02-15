package com.example.demo;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.security.auth.login.LoginException;

public class DemoApplication {

	public static void main(String[] args) {
		JDA api = null;
		try {
			api = JDABuilder.createDefault("ODEwMjMzNjkwMDUxMDUxNTYw.YCgq7Q.Xe5kD7MeuePWbQOoDN8DiaxmcQg").build();


			api.addEventListener(new MyEventListener());
			System.out.println("Бот успешно подключен.");
		} catch (LoginException e) {
			System.err.println("Ошибка при подключении бота к Discord: " + e.getMessage());
		}
	}

}
