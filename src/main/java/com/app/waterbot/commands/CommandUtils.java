package com.app.waterbot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandUtils {
    public static Long getChatId(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        } else {
            return update.getMessage().getChatId();
        }
    }

    /**
     * Get text of the message from {@link Update} object.
     *
     * @param update provided {@link Update}
     * @return the text of the message from the provided {@link Update} object.
     */
    public static String getMessage(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getText();
        } else {
            return update.getMessage().getText();
        }
    }

    public static Long getUserId(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        } else {
            return update.getMessage().getFrom().getId();
        }
    }
}
