package com.orangeteam.NewAuc.enums;

public enum ProductStatus {
    ANNOUNCED,
    IN_TRADES,
    BOOKED,
    SOLD,
    WITHDRAWN;

    public static String getText(ProductStatus status){
        switch (status){
            case SOLD: return "Продано";
            case BOOKED: return "Забронировано";
            case ANNOUNCED: return "Скоро в торгах";
            case IN_TRADES: return "В торгах";
            case WITHDRAWN: return "Снято с торгов";
            default: return "Ошибка";
        }
    }
}
