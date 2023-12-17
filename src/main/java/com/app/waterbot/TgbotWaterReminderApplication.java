package com.app.waterbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TgbotWaterReminderApplication {

    public static void main (String[] args) {
        SpringApplication.run(TgbotWaterReminderApplication.class, args);
    }

}
