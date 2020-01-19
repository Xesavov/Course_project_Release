package com.orangeteam.NewAuc.controllers;

import com.orangeteam.NewAuc.converters.TetenkaDtoConverter;
import com.orangeteam.NewAuc.dto.TetenkaDto;
import com.orangeteam.NewAuc.enums.Activity;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.models.UserDesc;
import com.orangeteam.NewAuc.models.UserProd;
import com.orangeteam.NewAuc.reps.EventRepository;
import com.orangeteam.NewAuc.reps.ProductRepository;
import com.orangeteam.NewAuc.reps.UserProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("tetenka")
public class TetenkaController {
    UserProdRepository userProdRepository;
    TetenkaDtoConverter tetenkaDtoConverter;
    ProductRepository productRepository;
    EventRepository eventRepository;

    @GetMapping
    String getTtnkPage(@RequestParam(defaultValue = "0") Long id, Model model){
        TetenkaDto info = new TetenkaDto();
        UserProd blank = userProdRepository.findBookedProduct(id);
        if(blank != null){
            info = tetenkaDtoConverter.convert(blank);
        }
        model.addAttribute("info", info);
        return "sendtovar";
    }

    @PostMapping
    String mark(@RequestParam(defaultValue = "0") Long id, Model model){
        UserProd blank = userProdRepository.findBookedProduct(id);
        if(blank == null){
            return "redirect:/tetenka?id="+id;
        }
        Product product = blank.getProduct();
        product.setStatus(ProductStatus.SOLD);
        product.setDatePay(LocalDateTime.now());
        productRepository.save(product);
        Event event = new Event();
        event.setUserProd(blank);
        event.setActivity(Activity.BUY);
        event.setDate(LocalDateTime.now());
        eventRepository.save(event);
        return "redirect:/tetenka?id="+id;
    }

    @Autowired
    public void setUserProdRepository(UserProdRepository userProdRepository) {
        this.userProdRepository = userProdRepository;
    }

    @Autowired
    public void setTetenkaDtoConverter(TetenkaDtoConverter tetenkaDtoConverter) {
        this.tetenkaDtoConverter = tetenkaDtoConverter;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
