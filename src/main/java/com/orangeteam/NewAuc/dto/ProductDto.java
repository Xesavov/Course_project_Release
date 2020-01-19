package com.orangeteam.NewAuc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
//    private List<CategoryDto> categories;
    private String leader;
    private boolean liked;
    private String assVal;
    private String step;
    private String currPrice;
    private String status;
    private String time;
    private String description;
    private List<String> images;
    private String attributes;
}
