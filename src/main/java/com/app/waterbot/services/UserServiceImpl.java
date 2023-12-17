package com.app.waterbot.services;

import com.app.waterbot.dtos.UserDto;
import com.app.waterbot.model.User;
import com.app.waterbot.repos.UserRepository;
import com.app.waterbot.util.UserToUserDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserToUserDtoConverter userToUserDtoConverter;

    @Override
    public User findByTgUserId(Long userTgId, Long chatId) {
        Optional<User> optionalUser = userRepo.findByTgUserId(userTgId);
        if (optionalUser.isEmpty()) {
            createUser(userTgId, chatId);
        }
        return userRepo.findByTgUserId(userTgId).get();
    }

    @Override
    public UserDto resetUser(Long userTgId, Long chatId) {
        Optional<User> optionalUser = userRepo.findByTgUserId(userTgId);
        optionalUser.ifPresent(this::deleteUser);
        return createUser(userTgId, chatId);
    }

    @Override
    public UserDto createUser(Long userTgId, Long chatId) {
        var user = new User();
        user.setTgUserId(userTgId);
        user.setChatId(chatId);
        user.setStartTime("08:00");
        user.setEndTime("22:00");
        user.setGapTime(10L);
        user.setTimer(user.getGapTime());
        user.setSleep(false);
        return userToUserDtoConverter.convert(userRepo.save(user));
    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    @Override
    public UserDto updateUser(User user) {
        User saved = userRepo.save(user);
        return userToUserDtoConverter.convert(saved);
    }

    @Override
    public void changeSleepState(User user) {
        user.setSleep(!user.getSleep());
        userRepo.save(user);
    }

    @Override
    public void resetTimer(User user) {
        user.setTimer(user.getGapTime());
        userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean decreaseTimerByOneMinute(User user) {
        Long timer = user.getTimer()-1;
        boolean needNotification = false;
        if (timer == 0) {
            timer = user.getGapTime();
            needNotification = true;
        }
        user.setTimer(timer);
        userRepo.save(user);
        return needNotification;
    }
}
