package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.RegistrationDto;
import com.orangeteam.NewAuc.models.UserDesc;
import com.orangeteam.NewAuc.reps.UserDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<RegistrationDto, UserDesc> {
    UserDescRepository userDescRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDesc convert(RegistrationDto registrationDto) {
        UserDesc user = new UserDesc();
        if (registrationDto.getLogin().equals("") ||
                registrationDto.getPassword1().equals("") ||
                registrationDto.getPassword2().equals("") ||
                registrationDto.getEmail().equals("") ||
                registrationDto.getFullName().equals("") ||
                registrationDto.getPhone().equals("")) {
            return null;
        }
        if (!registrationDto.getPassword1().equals(registrationDto.getPassword2())) {
            return null;
        }
        if (userDescRepository.findUserDescByLogin(registrationDto.getLogin()) != null) {
            return null;
        }
        user.setLogin(registrationDto.getLogin());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword1()));
        user.setEmail(registrationDto.getEmail());
        user.setFullName(registrationDto.getFullName());
        user.setPhone(registrationDto.getPhone());
        return user;
    }

    @Autowired
    public void setUserDescRepository(UserDescRepository userDescRepository) {
        this.userDescRepository = userDescRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
