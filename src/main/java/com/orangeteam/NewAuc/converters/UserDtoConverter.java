package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.UserDto;
import com.orangeteam.NewAuc.models.UserDesc;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<UserDesc, UserDto> {
    @Override
    public UserDto convert(UserDesc userDesc) {
        UserDto dto = new UserDto();
        dto.setLogin(userDesc.getLogin());
        dto.setFullName(userDesc.getFullName());
        dto.setEmail(userDesc.getEmail());
        dto.setPhone(userDesc.getPhone());
        return dto;
    }
}
