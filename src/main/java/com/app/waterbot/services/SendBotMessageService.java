package com.app.waterbot.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface SendBotMessageService {
    void sendMessage(Long chatId, String text);

    void sendMessage(SendMessage message);

}
