package pl.akademia.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademia.api.model.PromoCode;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PromoCodeRestController {

    private final PromoCodeService promoCodeService;

    public PromoCodeRestController(PromoCodeService promoCodeService) {
        this.promoCodeService = promoCodeService;
    }

    @PostMapping("/promocode/{code}/use")
    public ResponseEntity<PromoCode> usePromoCode(UUID promoCode){
        return new ResponseEntity<>(promoCodeService.usePromoCode(promoCode), HttpStatus.OK);
    }
}

