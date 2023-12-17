package com.app.waterbot.commands;


import com.app.waterbot.services.SendBotMessageService;
import com.app.waterbot.services.UserService;
import com.google.common.collect.ImmutableMap;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            UserService userService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(CommandName.RESET.getCommandName(), new ResetCommand(sendBotMessageService, userService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(CommandName.SET.getCommandName(), new SetCommand(sendBotMessageService, userService))
                .put(CommandName.SLEEP.getCommandName(), new SleepCommand(sendBotMessageService, userService))
                .put(CommandName.PROFILE.getCommandName(), new ProfileCommand(sendBotMessageService, userService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command findCommand(String commandName) {
        return commandMap.getOrDefault(commandName, unknownCommand);

    }
}
