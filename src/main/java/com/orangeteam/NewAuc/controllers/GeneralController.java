package com.orangeteam.NewAuc.controllers;

import com.orangeteam.NewAuc.converters.CategoryDtoConverter;
import com.orangeteam.NewAuc.dto.CategoryDto;
import com.orangeteam.NewAuc.models.Category;
import com.orangeteam.NewAuc.services.CategoryService;
import com.orangeteam.NewAuc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class GeneralController {
    UserService userService;
    CategoryService categoryService;
    CategoryDtoConverter categoryDtoConverter;

    @GetMapping()
    String getMainPage() {
        return "index";
    }

    @GetMapping("navbar")
    String getNavbar(Principal principal, Model model) {
        Category root = categoryService.getCategoryById(1L);
        List<CategoryDto> categories = categoryDtoConverter.convert(root).getSubsidiary();
        String name = null;
        String role = null;
        if (principal != null) {
            name = principal.getName();
            role = userService.getGeneralRole(name);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("name", name);
        model.addAttribute("role", role);
        return "navbar";
    }

    @GetMapping("about")
    String getAbout() {
        return "about";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCategoryDtoConverter(CategoryDtoConverter categoryDtoConverter) {
        this.categoryDtoConverter = categoryDtoConverter;
    }
}
