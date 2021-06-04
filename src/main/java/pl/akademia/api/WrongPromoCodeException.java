package pl.akademia.api;

import java.io.IOException;

public class WrongPromoCodeException extends NullPointerException {
    public WrongPromoCodeException(String used_promo_code) {
    }
}
