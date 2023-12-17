package com.app.waterbot.util;

import com.app.waterbot.dtos.UserDto;
import com.app.waterbot.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    @NonNull
    public UserDto convert(User source) {
        UserDto userDto = new UserDto();
        userDto.setTgUserId(source.getTgUserId());
        userDto.setChatId(source.getChatId());
        userDto.setEndTime(source.getEndTime());
        userDto.setStartTime(source.getStartTime());
        userDto.setGapTime(source.getGapTime());
        return userDto;
    }
}
