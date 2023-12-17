package com.app.waterbot.commands;

import com.app.waterbot.services.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final String HELP_TEXT = "Этот бот поможет тебе не забывать о том, что нужно и за собой следить, и воду пить. " +
            "Для тебя доступны следующие команды:\n\n" +
            "/reset - создание/пересоздание профиля с обнулением настроек на дефолтные;\n" +
            "/profile - получение текущих настроек профиля;\n" +
            "/set <старт HH:MM> <конец HH:MM> <промежуток в минутах> - установка новых настроек профиля;\n" +
            "Пример: /set 9:00 22:00 30\n" +
            "/sleep - изменение состояния сна на противоположное. Если сейчас бот не спит, то он будет писать тебе до установленного времени конца." +
            "И, наоборот, если бот спит, то он не будет писать, пока не настанет время начала. Но если что-то конкретно сейчас у тебя поменялось, ты можешь изменить это состояние сам.\n" +
            "/help - вывод данной подсказки с командами :).\n\n" +
            "Важно!! Если ты открыл бота первый раз, то обязательно выполни сначала /reset или пропиши /start и нажми кнопку.";

    @Override
    public void execute(Update update) {

        sendBotMessageService.sendMessage(CommandUtils.getChatId(update), HELP_TEXT);
    }
}
