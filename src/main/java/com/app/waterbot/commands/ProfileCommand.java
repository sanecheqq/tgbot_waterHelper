package com.app.waterbot.commands;

import com.app.waterbot.model.User;
import com.app.waterbot.services.SendBotMessageService;
import com.app.waterbot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class ProfileCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final UserService userService;

    @Override
    public void execute(Update update) {
        Long chatId = CommandUtils.getChatId(update);
        Long userId = CommandUtils.getUserId(update);

        User user = userService.findByTgUserId(userId, chatId);
        String message = String.format("Сейчас настройки твоего профиля таковы:\n" +
                        "Я начинаю приставать к тебе с напоминаниями о воде в %s, перестаю писать в %s. Пишу каждые(-ую) %d мин.",
                user.getStartTime(), user.getEndTime(), user.getGapTime());
        String sleepInfo =user.getSleep() ? "Сейчас я сплю." : "Сейчас я не сплю.";
        sendBotMessageService.sendMessage(chatId, message + sleepInfo);
    }
}
