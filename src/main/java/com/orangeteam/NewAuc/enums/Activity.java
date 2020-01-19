package com.orangeteam.NewAuc.enums;

public enum Activity {
    NOTLEADER,
    LEADER,
    LIKE,
    UNLIKE,
    WON,
    BUY;

    public static String getText(Activity activity) {
        switch (activity) {
            case LIKE:
                return "В избранном";
            case BUY:
                return "Куплено";
            case WON:
                return "Забронировано";
            case LEADER:
                return "Получено лидерство";
            case UNLIKE:
                return "Удалено из избранного";
            case NOTLEADER:
                return "Ставка перебита";
            default:
                return "Ошибка";
        }
    }
}
