package com.app.waterbot.bot;

import com.app.waterbot.commands.CommandContainer;
import com.app.waterbot.commands.CommandName;
import com.app.waterbot.config.BotConfig;
import com.app.waterbot.services.SendBotMessageServiceImpl;
import com.app.waterbot.services.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class WaterTgBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final CommandContainer commandContainer;

    @Autowired
    public WaterTgBot(BotConfig config, UserService userService) {
        this.config = config;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), userService);
    }

    @Override
    public void onUpdateReceived(@NotNull Update update) {
        if (update.hasCallbackQuery()) {
            String commandIdentifier = update.getCallbackQuery().getData();
            System.out.println(commandIdentifier);
            commandContainer.findCommand(commandIdentifier).execute(update);
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith("/")) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.findCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.findCommand(CommandName.NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
//    private void parseRequest(String receivedMessage, long chatId, String userName, Long userId) {
//        switch (receivedMessage.toLowerCase()) {
//            case "/start" -> startBot(chatId, userName);
//            case "/help" -> sendHelpText(chatId, HELP_TEXT);
//            case "/resetme" -> resetUserProfile(userId, chatId);
//            case "пошел нахуй" -> sendHelpText(chatId, "сам пошел нахуй.");
//            default -> sendHelpText(chatId, "Сорян, я ничего не понял.");
//        }
//    }

