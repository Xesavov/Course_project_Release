package com.orangeteam.NewAuc.controllers;

import com.orangeteam.NewAuc.converters.MainCardDtoConverter;
import com.orangeteam.NewAuc.converters.ProductDtoConverter;
import com.orangeteam.NewAuc.converters.PurCardDtoConverter;
import com.orangeteam.NewAuc.dto.MainCardDto;
import com.orangeteam.NewAuc.dto.ProductDto;
import com.orangeteam.NewAuc.dto.PurCardDto;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("product")
public class ProductController {
    ProductService productService;
    ProductDtoConverter productDtoConverter;
    MainCardDtoConverter mainCardDtoConverter;
    PurCardDtoConverter purCardDtoConverter;

    @GetMapping
    String getProductPage(@RequestParam Long id, Principal principal, Model model) {
        Product product = productService.getProductById(id);
        ProductDto productDto = productDtoConverter.convert(product);
        if (principal != null) {
            String name = principal.getName();
            productDto.setLeader(productService.isProductLeader(id, name));
            productDto.setLiked(productService.isProductLiked(id, name));
        }
        model.addAttribute("product", productDto);
        return "product";
    }

    @GetMapping("maincard")
    String getMainCard(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean allProducts, Principal principal, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("product", new MainCardDto());
            return "mainCard";
        }
        MainCardDto mainCardDto = mainCardDtoConverter.convert(product);
        if (principal != null) {
            String name = principal.getName();
            mainCardDto.setLeader(productService.isProductLeader(id, name));
            mainCardDto.setLiked(productService.isProductLiked(id, name));
            model.addAttribute("user", name);
        }
        model.addAttribute("product", mainCardDto);
        model.addAttribute("allProducts", allProducts);
        return "mainCard";
    }

    @GetMapping("purcard")
    String getPurCard(@RequestParam Long id, Model model) {
        Product product = productService.getProductById(id);
        PurCardDto purCardDto = purCardDtoConverter.convert(product);
        model.addAttribute("product", purCardDto);
        return "purCard";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductDtoConverter(ProductDtoConverter productDtoConverter) {
        this.productDtoConverter = productDtoConverter;
    }

    @Autowired
    public void setMainCardDtoConverter(MainCardDtoConverter mainCardDtoConverter) {
        this.mainCardDtoConverter = mainCardDtoConverter;
    }

    @Autowired
    public void setPurCardDtoConverter(PurCardDtoConverter purCardDtoConverter) {
        this.purCardDtoConverter = purCardDtoConverter;
    }
}
