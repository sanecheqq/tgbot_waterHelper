package com.app.waterbot.commands;

import com.app.waterbot.dtos.UserDto;
import com.app.waterbot.exceptions.ParseTimeException;
import com.app.waterbot.model.User;
import com.app.waterbot.services.SendBotMessageService;
import com.app.waterbot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class SetCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final UserService userService;

    private final String errorMessage = "Кажется, ты не умеешь читать. Повтори правила и проверь, как ты написал время.";
    private final String successMessage = "Ура, ты обновил свои настройки и даже нигде (почти) не налажал! Теперь ситуация такова:\n" +
            "Я начинаю приставать к тебе с напоминаниями о воде в %s, перестаю писать в %s. Пишу каждые(-ую) %d мин.";

    @Override
    public void execute(Update update) {
        Long chatId = CommandUtils.getChatId(update);
        Long userTgId = CommandUtils.getUserId(update);
        User user = userService.findByTgUserId(userTgId, chatId);

        String[] timeSettings = CommandUtils.getMessage(update).split(" ");
        if (timeSettings.length != 4) {
            sendBotMessageService.sendMessage(chatId, errorMessage);
        }

        try {
            validateTime(timeSettings[1]);
            validateTime(timeSettings[2]);
            validateMinutes(timeSettings[3]);
        } catch (ParseTimeException | NumberFormatException e) {
            sendBotMessageService.sendMessage(chatId, errorMessage);
        }

        user.setStartTime(timeSettings[1]);
        user.setEndTime(timeSettings[2]);
        user.setGapTime(Long.valueOf(timeSettings[3]));
        user.setTimer(Long.valueOf(timeSettings[3]));
        UserDto userDto = userService.updateUser(user);
        sendBotMessageService.sendMessage(chatId, String.format(successMessage, userDto.getStartTime(), userDto.getEndTime(), userDto.getGapTime()));
    }

    private void validateMinutes(String gapTime) {
        long minutes = Long.parseLong(gapTime);
        if (minutes < 5) {
            throw new ParseTimeException("Gap time must be more than 5 min.");
        }
    }

    private void validateTime(String time) {
        ParseTimeException exception = new ParseTimeException("Invalid time");
        if (time.length() < 3 || time.length() > 5) {
            throw exception;
        }
        String[] numbers = time.split(":");
        if (numbers.length != 2) {
            throw exception;
        }

        int hours = Integer.parseInt(numbers[0]);
        int minutes = Integer.parseInt(numbers[1]);
        if (hours > 23 || minutes > 59) {
            throw exception;
        }
    }
}
