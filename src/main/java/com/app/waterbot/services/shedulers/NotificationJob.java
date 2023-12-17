package com.app.waterbot.services.shedulers;

import com.app.waterbot.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationJob {

    private final NotificationService notificationService;

    @Scheduled(fixedRateString = "60000")
    public void notifyUsersWithExpiredTimer() {
        notificationService.notifyUsersWithExpiredTimer();
    }
}
