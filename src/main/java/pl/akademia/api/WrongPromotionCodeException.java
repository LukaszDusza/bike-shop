package pl.akademia.api;

import java.io.IOException;

public class WrongPromotionCodeException extends NullPointerException {
    public WrongPromotionCodeException(String used_promotion_code) {
    }
}
