package com.orangeteam.NewAuc.dto;

import lombok.Data;

@Data
public class MainCardDto {
    private Long id;
    private String name;
    private String status;
    private String leader;
    private String currPrice;
    private String assVal;
    private String step;
    private String time;
    private String image;
    private boolean liked;
}
