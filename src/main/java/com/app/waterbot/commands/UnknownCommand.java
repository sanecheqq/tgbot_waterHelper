package com.app.waterbot.commands;

import com.app.waterbot.services.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class UnknownCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    private final String UNKNOWN_TEXT = "Сорян, я ничего не понял. Если и ты тоже ничего не понял, то напиши /help.";

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(CommandUtils.getChatId(update), UNKNOWN_TEXT);
    }
}
