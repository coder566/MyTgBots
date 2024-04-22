package com.example.MyTgBots;

import com.example.MyTgBots.config.BotConfig;
import com.example.MyTgBots.service.TelegramBot;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@AllArgsConstructor
@SpringBootApplication
public class MyTgBotsApplication implements CommandLineRunner {

    private final BotConfig config;

    public static void main(String[] args) {
        SpringApplication.run(MyTgBotsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new TelegramBot(config));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
