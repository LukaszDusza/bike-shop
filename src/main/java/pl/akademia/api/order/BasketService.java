package pl.akademia.api.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.akademia.api.bike.Bike;
import pl.akademia.api.exceptions.BasketNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    public static final Logger logger = LoggerFactory.getLogger(BasketService.class);

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Basket getBasketById(long id) {
        logger.info("Try to return basket by id: {}.", id);
        Basket basket = basketRepository.findById(id).orElse(null);
        if (basket == null){
            logger.error("Exception: Order not found by id = {}.", id);
            throw new BasketNotFoundException("Order not found by id = " + id);
        }
        return basket;
    }

    public Basket createOrUpdateBasket(Basket basket) {
        basket.setBasketTotalPrice(getBasketTotalPrice(basket.getBikes()));
        return basketRepository.save(basket);
    }

    public Basket createOrUpdateDiscountedBasket(Basket basket, BigDecimal discount) {
        basket.setBasketTotalPrice(getBasketTotalPrice(basket.getBikes()).subtract(discount));
        return basketRepository.save(basket);
    }

    public BigDecimal getBasketTotalPrice(List<Bike> bikes) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (bikes.isEmpty()) {
            return totalPrice;
        }
        for (Bike b : bikes) {
            totalPrice = totalPrice.add(b.getPrice());
        }
        return totalPrice;
    }
}
