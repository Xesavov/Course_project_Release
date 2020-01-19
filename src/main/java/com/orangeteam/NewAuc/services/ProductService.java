package com.orangeteam.NewAuc.services;

import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.reps.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    public String isProductLeader(Long id, String name) {
        Integer leaderStatus = productRepository.isProductLeader(name, id);
        if(leaderStatus == null){
            return null;
        }
        if (leaderStatus == 2) {
            return "Вы лидируете";
        }
        if (leaderStatus == 1) {
            return "Ставка перебита";
        }
        return null;
    }

    public boolean isProductLiked(Long id, String name) {
        Integer like = productRepository.isProductLike(name, id);
        if (like == null){
            return false;
        }
        return like == 1;
    }

    public String priceToString(Double p) {
        int t = p.intValue();
        String s = "" + (p - t);
        s = s.replace("0.", ".");
        for (int k = 0; t > 0; k++, t /= 10) {
            s = t % 10 + s;
            if (k == 2) {
                s = " " + s;
                k = -1;
            }
        }
        return s.trim() + " руб.";
    }

    public String timeAMinusB(LocalDateTime a, LocalDateTime b) {
        if (a == null || b == null) {
            return null;
        }
        Duration duration = Duration.between(b, a);
        String result = "";
        long s = duration.getSeconds();
        if (s <= 0) {
            return "00:00:00";
        }
        long h = s / 3600;
        s %= 3600;
        long m = s / 60;
        s %= 60;
        return ((h < 10) ? "0" + h : h) + ":" + ((m < 10) ? "0" + m : m) + ":" + ((s < 10) ? "0" + s : s);
    }

    public List<Product> firstFilter(List<Product> products, Integer minPrice, Integer maxPrice, Integer minCost, Integer maxCost, boolean allProducts){
        List<Product> newList = new ArrayList<>();
        for(Product p: products){
            boolean mark = true;
            if(p.getCurrPrice()<minPrice){
                mark = false;
            }
            if(p.getAssVal()<minCost){
                mark = false;
            }
            if(p.getCurrPrice()>maxPrice && maxPrice>0){
                mark = false;
            }
            if(p.getAssVal()>maxCost && maxCost>0){
                mark = false;
            }
            if(!allProducts && p.getStatus()!= ProductStatus.IN_TRADES){
                mark = false;
            }

            if(mark){
                newList.add(p);
            }
        }
        return newList;
    }

    public List<Product> attribFilter(List<Product> products, String attributes){
        List<Product> nProds = new ArrayList<>();
        //
        nProds = products;
        //
        return nProds;
    }

    public List<Product> searchFilter(List<Product> products, String search){
        if(search == ""){
            return products;
        }
        List<Product> nProds = new ArrayList<>();
        String[] words = search.split(" ");
        for (Product p: products){
            int count = (int)Math.ceil(words.length*0.7);
            for(String w: words) {
                if (p.getId().toString().equals(w)){
                    count = 0;
                }
                if(p.getName().contains(w)){
                    count--;
                }
            }
            if(count<=0){
                nProds.add(p);
            }
        }
        return nProds;
    }

    public List<Product> sort(List<Product> products, String sort){
        if(sort.equals("name")){
            products.sort(Product.COMPARE_BY_NAME);
        }
        else if(sort.equals("price")){
            products.sort(Product.COMPARE_BY_PRICE);
        }
        else if(sort.equals("cost")){
            products.sort(Product.COMPARE_BY_COST);
        }
        else if(sort.equals("dateBeg")){
            products.sort(Product.COMPARE_BY_DATEBEG);
        }
        else {
            products.sort(Product.COMPARE_BY_DATEEND);
        }
        return products;
    }

    public List<Product> pagination(List<Product> products, Integer size, Integer page){
        int beg = Math.max((page-1)*size, 0);
        int end = Math.min(products.size(), page*size);
        if(beg>=end){
            beg = Math.max(end-size, 0);
        }
        List<Product> nProds = products.subList(beg, end);
        return nProds;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
