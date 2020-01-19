package com.orangeteam.NewAuc.dto;

import lombok.Data;

@Data
public class PurCardDto {
    private Long id;
    private String name;
    private String status;
    private String currPrice;
    private String datePay;
    private String image;
}
