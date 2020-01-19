package com.orangeteam.NewAuc.controllers;

import com.orangeteam.NewAuc.converters.NotificationDtoConverter;
import com.orangeteam.NewAuc.converters.UserDtoConverter;
import com.orangeteam.NewAuc.dto.NotificationDto;
import com.orangeteam.NewAuc.dto.UserDto;
import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.models.UserDesc;
import com.orangeteam.NewAuc.services.EventService;
import com.orangeteam.NewAuc.services.LikeService;
import com.orangeteam.NewAuc.services.UpService;
import com.orangeteam.NewAuc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("lk")
public class LkController {
    UserService userService;
    UserDtoConverter userDtoConverter;
    EventService eventService;
    NotificationDtoConverter notificationDtoConverter;
    UpService upService;
    LikeService likeService;

    @GetMapping
    String getLkPage(Principal principal) {
        return "lk";
    }

    @GetMapping("info")
    String getLkInfo(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        UserDesc userDesc = userService.getUserByLogin(name);
        UserDto userDto = userDtoConverter.convert(userDesc);
        model.addAttribute("user", userDto);
        return "lk-info";
    }

    @GetMapping("torgi")
    String getLkTorgi(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        List<Long> products = userService.getActivProductsByLogin(name).stream().map(x -> x.getId()).collect(Collectors.toList());
        model.addAttribute("list", products);
        return "lk-torgi";
    }

    @GetMapping("like")
    String getLkLiked(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        List<Long> products = userService.getLikedProductsByLogin(name).stream().map(x -> x.getId()).collect(Collectors.toList());
        Collections.reverse(products);
        model.addAttribute("list", products);
        return "lk-like";
    }

    @GetMapping("hist")
    String getLkPur(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        List<Long> products = userService.getPurProductsByLogin(name).stream().map(x -> x.getId()).collect(Collectors.toList());
        Collections.reverse(products);
        model.addAttribute("list", products);
        return "lk-pur";
    }

    @GetMapping("notif")
    String getNotif(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        List<Long> events = eventService.getHistoryByLogin(name).stream().map(x -> x.getId()).collect(Collectors.toList());
        Collections.reverse(events);
        model.addAttribute("list", events);
        return "lk-notif";
    }

    @GetMapping("notifcard")
    String getNotifCard(@RequestParam Long id, Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String name = principal.getName();
        Event event = eventService.getEventById(id);
        NotificationDto notificationDto = null;
        if (event.getUserProd().getUserDesc().getLogin().equals(name)) {
            notificationDto = notificationDtoConverter.convert(event);
        }
        model.addAttribute("notif", notificationDto);
        return "notifCard";
    }

    @PostMapping("/up")
    String up(@RequestParam("product") Long product,
              @RequestParam("steps") Integer steps, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        upService.up(product, steps, principal.getName());
        return "redirect:/product&id=" + product;
    }

    @PostMapping("/like")
    String like(@RequestParam("product") Long product, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        likeService.setLike(product, principal.getName());
        return "redirect:/product/" + product;
    }

    @PostMapping("/unlike")
    String unlike(@RequestParam("product") Long product, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        likeService.setUnLike(product, principal.getName());
        return "redirect:/product/" + product;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDtoConverter(UserDtoConverter userDtoConverter) {
        this.userDtoConverter = userDtoConverter;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setNotificationDtoConverter(NotificationDtoConverter notificationDtoConverter) {
        this.notificationDtoConverter = notificationDtoConverter;
    }

    @Autowired
    public void setUpService(UpService upService) {
        this.upService = upService;
    }

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }
}
