package pl.akademia.api.promotion;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.exceptions.BikeNotFoundException;
import pl.akademia.api.exceptions.PromoCodeNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PromotionCodeRestController {

    private final PromotionCodeService promotionCodeService;

    public PromotionCodeRestController(PromotionCodeService promotionCodeService) {
        this.promotionCodeService = promotionCodeService;
    }

    @PostMapping("/promocodes")
    public ResponseEntity<PromotionCode> createPromotionCode(@RequestParam int activeDays, BigDecimal discount){
        return new ResponseEntity<>(promotionCodeService.createPromotionCode(activeDays, discount), HttpStatus.CREATED);
    }

    @GetMapping("/promocodes/{a}")
    public ResponseEntity<List<PromotionCode>> getAllPromotionCodes(@RequestParam(required = false) String a){
        List<PromotionCode> promotionCodes;
        if ("active".equals(a)) promotionCodes = promotionCodeService.getActivePromotionCodeByCode();
        else if ("inactive".equals(a)) promotionCodes = promotionCodeService.getInactivePromotionCodeByCode();
        else if (a == null) promotionCodes = promotionCodeService.getAllPromotionCode();
        else {
            throw new PromoCodeNotFoundException("Promo codes were not found! ");
        }
        if (promotionCodes.isEmpty()) {
            throw new PromoCodeNotFoundException("Promo codes were not found! ");
        }
        return new ResponseEntity<>(promotionCodes, HttpStatus.OK);
    }

    @GetMapping("/promocodes/{promocode}")
    public ResponseEntity<PromotionCode> getPromotionCodeByCode(@RequestParam String promocode){
        if (promotionCodeService.getPromotionCodeByCode(promocode) == null)
            throw new PromoCodeNotFoundException("Promo code  does not exists! ");
        return new ResponseEntity<>(promotionCodeService.getPromotionCodeByCode(promocode), HttpStatus.OK);
    }

    @PostMapping("/promocodes/{promocode}/use")
    public ResponseEntity<PromotionCode> usePromotionCode(@PathVariable String promocode){
        try{
            promotionCodeService.usePromotionCode(promocode);
        } catch(WrongPromotionCodeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/promocode/{id}")
    public ResponseEntity<PromotionCode> getPromotionCodeById(@PathVariable Long id){
        if (promotionCodeService.getPromotionCodeById(id) == null) {
            throw new PromoCodeNotFoundException("Promo code by this ID does not exists! ");
        }

        return new ResponseEntity<>(promotionCodeService.getPromotionCodeById(id), HttpStatus.OK);
    }
    @DeleteMapping("/promocode/{id}/delete")
    public ResponseEntity<PromotionCode> deletePromotionCodeById(@PathVariable Long id){
        if (promotionCodeService.deletePromotionCodesById(id) > 0) return new ResponseEntity<>(HttpStatus.OK);
        else throw new PromoCodeNotFoundException("Promo code by this ID does not exists! ");
    }



}

