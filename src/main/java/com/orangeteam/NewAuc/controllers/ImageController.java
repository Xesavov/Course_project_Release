package com.orangeteam.NewAuc.controllers;

import com.orangeteam.NewAuc.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ImageController {
    ImageService imageService;

    @GetMapping("image/{name}")
    @ResponseBody
    void getImage(@PathVariable String name, HttpServletResponse response) {
        if(name.equals("null")){
            name = "i.jpg";
        }
        byte[] bytes = imageService.getImage(name);
        try {
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
