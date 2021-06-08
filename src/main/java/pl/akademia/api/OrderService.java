package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Order;
import pl.akademia.api.model.PromoCode;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final BasketService basketService;
    private final PromoCodeService promoCodeService;

    public OrderService(OrderRepository orderRepository,
                        ClientRepository clientRepository,
                        AddressRepository addressRepository,
                        BasketService basketService,
                        PromoCodeService promoCodeService) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.basketService = basketService;
        this.promoCodeService = promoCodeService;
    }

    public Order creatOrder(Order order, long basketId){
        addressRepository.save(order.getClient().getAddress());
        order.getClient().setRegistrationDate(Date.valueOf(LocalDate.now()));
        clientRepository.save(order.getClient());
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        return orderRepository.save(order);
    }

    public Order creatOrder(Order order, long basketId, String promoCode){
        addressRepository.save(order.getClient().getAddress());
        order.getClient().setRegistrationDate(Date.valueOf(LocalDate.now()));
        clientRepository.save(order.getClient());
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        UUID input = UUID.fromString(promoCode);


        return orderRepository.save(order);
    }
}
