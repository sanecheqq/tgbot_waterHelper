package com.app.waterbot.commands;

import lombok.Getter;

@Getter
public enum CommandName {
    START("/start"),
    RESET("/reset"),
    HELP("/help"),
    SET("/set"),
    SLEEP("/sleep"),
    PROFILE("/profile"),
    NO("nocommand");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }
}