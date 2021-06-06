package pl.akademia.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.model.PromoCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PromoCodeRestController {

    private final PromoCodeService promoCodeService;

    public PromoCodeRestController(PromoCodeService promoCodeService) {
        this.promoCodeService = promoCodeService;
    }

    @PostMapping("/promocodes")
    public ResponseEntity<PromoCode> createPromoCode(@RequestBody int activeDays, BigDecimal discount){
        return new ResponseEntity<>(promoCodeService.createPromoCode(activeDays, discount), HttpStatus.CREATED);
    }

    @GetMapping("/promocodes")
    public ResponseEntity<List<PromoCode>> getAllPromoCodes(){
        List<PromoCode> promoCodes = promoCodeService.getAllPromoCode();
        if (promoCodes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(promoCodes, HttpStatus.OK);
    }

    @GetMapping("/promocodes/{promocode}")
    public ResponseEntity<PromoCode> getPromoCodeByCode(@PathVariable UUID promocode){
        if (promoCodeService.getPromoCodeByCode(promocode) == null) return new ResponseEntity<>(promoCodeService.getPromoCodeByCode(promocode), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(promoCodeService.getPromoCodeByCode(promocode), HttpStatus.OK);
    }
    @PostMapping("/promocodes/{promocode}/use")
    public ResponseEntity<PromoCode> usePromoCode(@PathVariable UUID promocode){
        try{
            promoCodeService.usePromoCode(promocode);
        } catch(WrongPromoCodeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(promoCodeService.usePromoCode(promocode), HttpStatus.OK);
    }

    @GetMapping("/promocode/{id}")
    public ResponseEntity<PromoCode> getPromoCodeById(@PathVariable Long id){
        if (promoCodeService.getPromoCodeById(id) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(promoCodeService.getPromoCodeById(id), HttpStatus.OK);
    }
    @DeleteMapping("/promocode/{id}/delete")
    public ResponseEntity<PromoCode> deletePromoCodeById(@PathVariable Long id){
        if (promoCodeService.deletePromoCodesById(id) > 0) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/promocode/{id}/update")
    public ResponseEntity<PromoCode> updateExpDatePromoCodeById(@PathVariable Long id, @RequestBody int activeDays){
        try{
            promoCodeService.updateExpDatePromoCodeById(id, activeDays);
        }catch(WrongPromoCodeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(promoCodeService.updateExpDatePromoCodeById(id, activeDays), HttpStatus.OK);
    }


}

