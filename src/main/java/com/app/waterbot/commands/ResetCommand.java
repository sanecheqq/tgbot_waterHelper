package com.app.waterbot.commands;

import com.app.waterbot.dtos.UserDto;
import com.app.waterbot.services.SendBotMessageService;
import com.app.waterbot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class ResetCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final UserService userService;

    private final String RESET_TEXT = """
                Ура, ты справился и смог пересоздать/создать свой профиль! Сейчас настройки твоего профиля таковы:\n
                Я начинаю приставать к тебе с напоминаниями о воде в %s, перестаю писать в %s. Пишу каждые(-ую) %d мин.\n
                Если такой режим для тебя невыносим, ты можешь отредактировать данные следующим образом. Напиши предпочитаемое время в следующем формате (HH:MM):
                /set <время старта> <время окончания> <промежуток между напоминаниями в минутах>
                Учти, что промежуток между напоминаниями не может быть менее 5 мин.\n
                Пример: /set 08:00 22:00 60\n
                Ты красава!
                """;

    @Override
    public void execute(Update update) {
        Long chatId = CommandUtils.getChatId(update);
        Long userId = CommandUtils.getUserId(update);
        UserDto userDto = userService.resetUser(userId, chatId);
        String text = String.format(RESET_TEXT, userDto.getStartTime(), userDto.getEndTime(), userDto.getGapTime());
        sendBotMessageService.sendMessage(chatId, text);
    }
}
