package pl.akademia.api.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger logger = LoggerFactory.getLogger(BasketRestController.class);

    public BasketRestController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/baskets")
    public ResponseEntity<Basket> createOrUpdateBasket(@RequestBody Basket basket) {
        if (basket.getId() == null) {
            logger.info("Try to create basket.");
            return new ResponseEntity<>(basketService.createOrUpdateBasket(basket), HttpStatus.CREATED);
        }
        logger.info("Try to update basket. Basket id = {}", basket.getId());
        return new ResponseEntity<>(basketService.createOrUpdateBasket(basket), HttpStatus.OK);
    }

    @PostMapping("/baskets/discount")
    public ResponseEntity<Basket> createOrUpdateDiscountedBasket(@RequestBody Basket basket,
                                                                 @RequestParam BigDecimal discount) {
        if (basket.getId() == null) {
            logger.info("Try to create discounted basket.");
            return new ResponseEntity<>(basketService.createOrUpdateDiscountedBasket(basket, discount), HttpStatus.CREATED);
        }
        logger.info("Try to update discounted basket. Basket id = {}", basket.getId());
        return new ResponseEntity<>(basketService.createOrUpdateDiscountedBasket(basket, discount), HttpStatus.OK);
    }
}
