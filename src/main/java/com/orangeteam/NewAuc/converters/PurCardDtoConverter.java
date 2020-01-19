package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.PurCardDto;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Image;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
public class PurCardDtoConverter implements Converter<Product, PurCardDto> {
    ProductService productService;

    @Override
    public PurCardDto convert(Product product) {
        PurCardDto dto = new PurCardDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setStatus(ProductStatus.getText(product.getStatus()));
        dto.setCurrPrice(productService.priceToString(product.getCurrPrice()));
        if (product.getDatePay() != null) {
            dto.setDatePay(product.getDatePay().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        } else {
            dto.setDatePay("");
        }
        String image = null;
        for (Image i : product.getImages()) {
            if (i.getType() == 0) {
                image = "i" + i.getId() + i.getExt();
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
