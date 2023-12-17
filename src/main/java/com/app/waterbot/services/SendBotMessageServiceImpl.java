package com.app.waterbot.services;

import com.app.waterbot.bot.WaterTgBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final WaterTgBot bot;

    @Override
    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        try {
            bot.execute(message);
//            log.info("Reply sent");
        } catch (TelegramApiException e){
//            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sendMessage(SendMessage message) {
        try {
            bot.execute(message);
//            log.info("Reply sent");
        } catch (TelegramApiException e){
//            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
