package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.MainCardDto;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Image;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MainCardDtoConverter implements Converter<Product, MainCardDto> {
    private ProductService productService;

    @Override
    public MainCardDto convert(Product product) {
        MainCardDto dto = new MainCardDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setStatus(ProductStatus.getText(product.getStatus()));
        dto.setAssVal(productService.priceToString(product.getAssVal()));
        dto.setStep(productService.priceToString(product.getStep()));
        dto.setCurrPrice(productService.priceToString(product.getCurrPrice()));
        String time = null;
        if (product.getStatus() == ProductStatus.ANNOUNCED && product.getDateBeg() != null) {
            time = "Время до начала: " + productService.timeAMinusB(product.getDateBeg(), LocalDateTime.now());
        }
        if (product.getStatus() == ProductStatus.IN_TRADES && product.getDateEnd() != null) {
            time = "Осталось времени: " + productService.timeAMinusB(product.getDateEnd(), LocalDateTime.now());
        }
        dto.setTime(time);
        String image = null;
        for (Image i: product.getImages()){
            if(i.getType()==0){
                image = "i"+i.getId()+i.getExt();
                break;
            }
        }
        dto.setImage(image);
        return dto;
    }
@Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
