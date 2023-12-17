package com.app.waterbot.services;

import com.app.waterbot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void notifyUsersWithExpiredTimer() {
        List<User> userList = userService.getAllUsers();
        LocalTime now = LocalTime.now();
        int nowHour = now.getHour();
        int nowMinute = now.getMinute();

        for (var user : userList) {
            String[] endTime = user.getEndTime().split(":");
            int endHour = Integer.parseInt(endTime[0]);
            int endMinute = Integer.parseInt(endTime[1]);

            if (!user.getSleep() && (nowHour != endHour || nowMinute <= endMinute)) {
                boolean needNotification = userService.decreaseTimerByOneMinute(user);
                if (needNotification) {
                    sendBotMessageService.sendMessage(user.getChatId(), "Пора пить воду!");
                }
            } else {
                if (!user.getSleep()) {
                    userService.changeSleepState(user);
                }
            }

            String[] startTime = user.getStartTime().split(":");
            int startHour = Integer.parseInt(startTime[0]);
            int startMinute = Integer.parseInt(startTime[1]);

            if (user.getSleep() && nowHour == startHour && nowMinute >= startMinute) {
                userService.changeSleepState(user);
                userService.resetTimer(user);
                sendBotMessageService.sendMessage(user.getChatId(), "Добрейшего времени суток! Настал новый день питья воды! Ура!");
            }
        }
    }


}
