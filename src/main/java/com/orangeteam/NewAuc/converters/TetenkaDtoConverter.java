package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.TetenkaDto;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Image;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.models.UserDesc;
import com.orangeteam.NewAuc.models.UserProd;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TetenkaDtoConverter implements Converter<UserProd, TetenkaDto> {
    @Override
    public TetenkaDto convert(UserProd userProd) {
        TetenkaDto dto = new TetenkaDto();
        Product product = userProd.getProduct();
        UserDesc user = userProd.getUserDesc();
        dto.setId(product.getId());
        List<Image> images = product.getImages();
        for(Image i: images){
            if(i.getType()==0){
                dto.setImage("i"+i.getId()+i.getExt());
                break;
            }
        }
        dto.setStatus(ProductStatus.getText(product.getStatus()));
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }
}
