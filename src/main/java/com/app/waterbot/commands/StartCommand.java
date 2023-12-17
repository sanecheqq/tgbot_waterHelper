package com.app.waterbot.commands;

import com.app.waterbot.services.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    final String GREETINGS_TEXT = "Здарова, Избранный! Я Telegram-бот водопоец.";
    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(CommandUtils.getChatId(update)));
        message.setText(GREETINGS_TEXT);
        message.setReplyMarkup(Buttons.inlineMarkup());

        sendBotMessageService.sendMessage(message);
    }
}
