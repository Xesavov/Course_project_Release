package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.ProductDto;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.services.AttributeService;
import com.orangeteam.NewAuc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class ProductDtoConverter implements Converter<Product, ProductDto> {
    CategoryDtoConverter categoryDtoConverter;
    AttributeService attributeService;
    ProductService productService;

    @Override
    public ProductDto convert(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setAssVal(productService.priceToString(product.getAssVal()));
        dto.setStep(productService.priceToString(product.getStep()));
        dto.setCurrPrice(productService.priceToString(product.getCurrPrice()));
        dto.setStatus(ProductStatus.getText(product.getStatus()));
        dto.setDescription(product.getDescription());
        dto.setImages(product.getImages().stream().map(x -> "i" + x.getId() + x.getExt()).collect(Collectors.toList()));
        String time = null;
        if (product.getStatus() == ProductStatus.ANNOUNCED && product.getDateBeg() != null) {
            time = "Время до начала: " + productService.timeAMinusB(product.getDateBeg(), LocalDateTime.now());
        }
        if (product.getStatus() == ProductStatus.IN_TRADES && product.getDateEnd() != null) {
            time = "Осталось времени: " + productService.timeAMinusB(product.getDateEnd(), LocalDateTime.now());
        }
        dto.setTime(time);
        dto.setAttributes(attributeService.getAttributesByProduct(product));
        return dto;
    }

    @Autowired
    public void setCategoryDtoConverter(CategoryDtoConverter categoryDtoConverter) {
        this.categoryDtoConverter = categoryDtoConverter;
    }

    @Autowired
    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
