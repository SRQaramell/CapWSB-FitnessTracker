package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailSimpleDto;
import org.springframework.stereotype.Component;

@Component
class UserEmailSimpleMapper
{
    UserEmailSimpleDto toEmailSimpleDto(User user)
    {
        return new UserEmailSimpleDto(user.getID(), user.getEmail());
    }

    User toSimpleEmailEntity(UserEmailSimpleDto userDto)
    {
        return new User(null, null, null, userDto.email());
    }
}