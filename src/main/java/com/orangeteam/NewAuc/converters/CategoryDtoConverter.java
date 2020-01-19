package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.CategoryDto;
import com.orangeteam.NewAuc.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter implements Converter<Category, CategoryDto> {
    @Override
    public CategoryDto convert(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setSubsidiary(category.getSubsidiary().stream().map(this::convert).collect(Collectors.toList()));
        return dto;
    }
}
