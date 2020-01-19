package com.orangeteam.NewAuc.dto;

import lombok.Data;

@Data
public class NotificationDto {
    Long id;
    Long productId;
    String name;
    String text;
    String date;
    String image;
}
