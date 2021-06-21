package pl.akademia.api.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/api/v1")
public class BasketRestController {
    private final BasketService basketService;

    public BasketRestController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/baskets")
    public ResponseEntity<Basket> createOrUpdateBasket(@RequestBody Basket basket) {
        if (basket.getId() == null) {
            return new ResponseEntity<>(basketService.createOrUpdateBasket(basket), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(basketService.createOrUpdateBasket(basket), HttpStatus.OK);
    }

    @PostMapping("/baskets/discount")
    public ResponseEntity<Basket> createOrUpdateDiscountedBasket(@RequestBody Basket basket,
                                                                 @RequestParam BigDecimal discount) {
        if (basket.getId() == null) {
            return new ResponseEntity<>(basketService.createOrUpdateDiscountedBasket(basket, discount), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(basketService.createOrUpdateDiscountedBasket(basket, discount), HttpStatus.OK);
    }
}
