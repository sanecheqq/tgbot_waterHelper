package com.app.waterbot.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private Long tgUserId;
    private Long chatId;
    private String startTime;
    private String endTime;
    private Long gapTime;
}
