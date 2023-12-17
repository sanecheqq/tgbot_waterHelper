package com.app.waterbot.services;

import com.app.waterbot.dtos.UserDto;
import com.app.waterbot.model.User;

import java.util.List;

public interface UserService {

    User findByTgUserId(Long userTgId, Long chatId);
    UserDto resetUser(Long userTgId, Long chatId);
    UserDto createUser(Long userTgId, Long chatId);
    void deleteUser(User user);
    UserDto updateUser(User user);

    void changeSleepState(User user);
    void resetTimer(User user);

    List<User> getAllUsers();

    boolean decreaseTimerByOneMinute(User user);
}
