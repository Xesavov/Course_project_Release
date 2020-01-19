package com.orangeteam.NewAuc.controllers;

import com.orangeteam.NewAuc.converters.UserConverter;
import com.orangeteam.NewAuc.dto.RegistrationDto;
import com.orangeteam.NewAuc.models.UserDesc;
import com.orangeteam.NewAuc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthControler {
    private UserService userService;
    private UserConverter userConverter;

    @GetMapping("login")
    String getLoginPage(){
        return "login";
    }

    @GetMapping("registration")
    String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam(name = "login") String login,
                             @RequestParam(name = "fullName") String fullName,
                             @RequestParam(name = "pass1") String pass1,
                             @RequestParam(name = "pass2") String pass2,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "phone") String phone){
        /*if(!pass1.equals(pass2)){
            return "redirect:/registration";
        }*/
        RegistrationDto dto = new RegistrationDto();
        dto.setLogin(login);
        dto.setFullName(fullName);
        dto.setPassword1(pass1);
        dto.setPassword2(pass2);
        dto.setEmail(email);
        dto.setPhone(phone);
        UserDesc user = userConverter.convert(dto);
        if(user==null){
            return "redirect:/registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }
}
