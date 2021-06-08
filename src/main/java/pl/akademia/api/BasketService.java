package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Basket;

@Service
public class BasketService {
    public final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Basket creatBasket(Basket basket){
        return basketRepository.save(basket);
    }

    public Basket getBasketById(long id){
        return basketRepository.findById(id).orElse(null);
    }
}
