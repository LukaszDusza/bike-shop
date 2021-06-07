package pl.akademia.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.model.PromotionCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PromotionCodeRestController {

    private final PromotionCodeService promotionCodeService;

    public PromotionCodeRestController(PromotionCodeService promotionCodeService) {
        this.promotionCodeService = promotionCodeService;
    }

    @PostMapping("/promocodes")
    public ResponseEntity<PromotionCode> createPromotionCode(@RequestBody int activeDays, BigDecimal discount){
        return new ResponseEntity<>(promotionCodeService.createPromotionCode(activeDays, discount), HttpStatus.CREATED);
    }

    @GetMapping("/promocodes")
    public ResponseEntity<List<PromotionCode>> getAllPromotionCodes(){
        List<PromotionCode> promotionCodes = promotionCodeService.getAllPromotionCode();
        if (promotionCodes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(promotionCodes, HttpStatus.OK);
    }

    @GetMapping("/promocodes/{promocode}")
    public ResponseEntity<PromotionCode> getPromotionCodeByCode(@PathVariable UUID promocode){
        if (promotionCodeService.getPromotionCodeByCode(promocode) == null) return new ResponseEntity<>(promotionCodeService.getPromotionCodeByCode(promocode), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(promotionCodeService.getPromotionCodeByCode(promocode), HttpStatus.OK);
    }
    @PostMapping("/promocodes/{promocode}/use")
    public ResponseEntity<PromotionCode> usePromotionCode(@PathVariable UUID promocode){
        try{
            promotionCodeService.usePromotionCode(promocode);
        } catch(WrongPromoCodeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(promotionCodeService.usePromotionCode(promocode), HttpStatus.OK);
    }

    @GetMapping("/promocode/{id}")
    public ResponseEntity<PromotionCode> getPromotionCodeById(@PathVariable Long id){
        if (promotionCodeService.getPromotionCodeById(id) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(promotionCodeService.getPromotionCodeById(id), HttpStatus.OK);
    }
    @DeleteMapping("/promocode/{id}/delete")
    public ResponseEntity<PromotionCode> deletePromotionCodeById(@PathVariable Long id){
        if (promotionCodeService.deletePromotionCodesById(id) > 0) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/promocode/{id}/update")
    public ResponseEntity<PromotionCode> updateExpDatePromotionCodeById(@PathVariable Long id, @RequestBody int activeDays){
        try{
            promotionCodeService.updateExpDatePromotionCodeById(id, activeDays);
        }catch(WrongPromoCodeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(promotionCodeService.updateExpDatePromotionCodeById(id, activeDays), HttpStatus.OK);
    }


}

