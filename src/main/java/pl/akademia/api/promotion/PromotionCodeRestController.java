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

    @GetMapping("/promocodes/{promoCodeStatus}")
    public ResponseEntity<List<PromotionCode>> getAllPromotionCodes(@RequestParam(required = false) String promoCodeStatus){

        List<PromotionCode> promotionCodes = null;
        if (promotionCodes.isEmpty()) {
            throw new PromoCodeNotFoundException("Promo code  does not exists! ");
        }
        if ("active".equals(promoCodeStatus)) {
            promotionCodes = promotionCodeService.getActivePromotionCodeByCode();
            return new ResponseEntity<>(promotionCodes, HttpStatus.OK);}

        if ("inactive".equals(promoCodeStatus)){
            promotionCodes = promotionCodeService.getInactivePromotionCodeByCode();
            return new ResponseEntity<>(promotionCodes, HttpStatus.OK);}

        if (promoCodeStatus == null){
            promotionCodes = promotionCodeService.getAllPromotionCode();}
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
        if (promotionCodeService.deletePromotionCodesById(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);}
        throw new PromoCodeNotFoundException("Promo code by this ID does not exists! ");
    }



}

