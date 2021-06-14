package pl.akademia.api.order;

import org.springframework.stereotype.Service;
import pl.akademia.api.bike.Bike;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Basket createOrUpdateBasket(Basket basket) {
        basket.setBasketTotalPrice(getBasketTotalPrice(basket.getBikes()));
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