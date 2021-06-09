package pl.akademia.api.promotion;

public class WrongPromotionCodeException extends NullPointerException {
    public WrongPromotionCodeException(String used_promotion_code) {
    }
}

