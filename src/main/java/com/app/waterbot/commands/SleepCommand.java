package com.app.waterbot.commands;

import com.app.waterbot.model.User;
import com.app.waterbot.services.SendBotMessageService;
import com.app.waterbot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class SleepCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final UserService userService;

    @Override
    public void execute(Update update) {
        Long userId = CommandUtils.getUserId(update);
        Long chatId = CommandUtils.getChatId(update);

        User user = userService.findByTgUserId(userId, chatId);
        String text;
        if (!user.getSleep()) {
            text = String.format("Твой статус сна изменен. Теперь я не буду писать, пока не настанет %s.", user.getStartTime());
        } else {
            text = String.format("Твой статус сна изменен. Теперь я буду писать, пока не настанет %s.", user.getEndTime());
        }
        userService.changeSleepState(user);
        sendBotMessageService.sendMessage(chatId, text);
    }
}
