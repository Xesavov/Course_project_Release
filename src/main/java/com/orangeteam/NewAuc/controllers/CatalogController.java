package com.orangeteam.NewAuc.controllers;

import com.orangeteam.NewAuc.converters.CategoryDtoConverter;
import com.orangeteam.NewAuc.models.Category;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.services.CategoryService;
import com.orangeteam.NewAuc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatalogController {
    CategoryService categoryService;
    CategoryDtoConverter categoryDtoConverter;
    ProductService productService;

    @GetMapping("catalog")
    String getCatalogPage(@RequestParam(name = "catId", defaultValue = "1") Long catId,
                          @RequestParam(defaultValue = "") String search,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "6") Integer size,
                          @RequestParam(defaultValue = "0") Integer minPrice,
                          @RequestParam(defaultValue = "0") Integer maxPrice,
                          @RequestParam(defaultValue = "0") Integer minCost,
                          @RequestParam(defaultValue = "0") Integer maxCost,
                          @RequestParam(defaultValue = "false") boolean allProducts,
                          @RequestParam(defaultValue = "") String attributes,
                          @RequestParam(defaultValue = "time") String sort,
                          Model model) {
        Category category = categoryService.getCategoryById(catId);
        List<Category> categories = category.getSubsidiary();
        model.addAttribute("categories", categories.stream().map(categoryDtoConverter::convert).collect(Collectors.toList()));
        model.addAttribute("name", category.getName());
        model.addAttribute("allProducts", allProducts);

        List<Product> products = categoryService.getProductsByCategory(category);
        products = products.stream().distinct().collect(Collectors.toList());
        products = productService.firstFilter(products, minPrice, maxPrice, minCost, maxCost, allProducts);
        products = productService.searchFilter(products, search);
        products = productService.attribFilter(products, attributes);
        products = productService.sort(products, sort);


        final int dist = 4;
        double length = products.size();
        int maxPage = (int) Math.ceil(length/size);
        if(page>maxPage){
            page=maxPage;
        }
        if(page<1){
            page=1;
        }
        List<Integer> prevNumb = new ArrayList<>();
        for (int i = Math.max(1, page-dist); i<page; i++){
            prevNumb.add(i);
        }
        List<Integer> nextNumb = new ArrayList<>();
        for (int i = page+1; i<=Math.min(maxPage, page+dist); i++){
            nextNumb.add(i);
        }
        products = productService.pagination(products, size, page);

        model.addAttribute("list", products.stream().map(x -> x.getId()).collect(Collectors.toList()));
        model.addAttribute("prefix", "/catalog?id=");
        model.addAttribute("prevpage", page-1);
        model.addAttribute("prevNumbs", prevNumb);
        model.addAttribute("thisPage", page);
        model.addAttribute("nextNumbs", nextNumb);
        model.addAttribute("nextpage", page+1);
        model.addAttribute("thisid", category.getId());
        return "catalog";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCategoryDtoConverter(CategoryDtoConverter categoryDtoConverter) {
        this.categoryDtoConverter = categoryDtoConverter;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
