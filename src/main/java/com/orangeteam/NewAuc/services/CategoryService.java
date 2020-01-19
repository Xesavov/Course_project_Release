package com.orangeteam.NewAuc.services;

import com.orangeteam.NewAuc.models.Category;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.reps.CategoryRepository;
import com.orangeteam.NewAuc.reps.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    public Category getCategoryById(Long id){
        Category category = categoryRepository.getOne(id);
        return category;
    }

    public List<Product> getProductsByCategory(Category category){
        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.getProductsByCategoryId(category.getId()));
        for(Category c: category.getSubsidiary()){
            products.addAll(getProductsByCategory(c));
        }
        return products;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
