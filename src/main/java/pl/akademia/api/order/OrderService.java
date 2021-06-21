package pl.akademia.api.order;

import org.springframework.stereotype.Service;
import pl.akademia.api.client.Client;
import pl.akademia.api.client.ClientService;
import pl.akademia.api.promotion.PromotionCodeService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final BasketService basketService;
    private final PromotionCodeService promotionCodeService;

    public OrderService(OrderRepository orderRepository,
                        ClientService clientService,
                        BasketService basketService,
                        PromotionCodeService promotionCodeService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.basketService = basketService;
        this.promotionCodeService = promotionCodeService;
    }

    public Order createOrder(Order order, long basketId) {
        addOrUpdateClient(order, basketId);
        order.setBasket(basketService.getBasketById(basketId));
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        return orderRepository.save(order);
    }

    public Order createOrder(Order order, long basketId, String promotionCode) {
        addOrUpdateClient(order, basketId);
        order.setPromoCode(promotionCodeService.usePromotionCode(promotionCode));
        basketService.createOrUpdateDiscountedBasket(basketService.getBasketById(basketId),
                promotionCodeService.getPromotionCodeByCode(promotionCode).getDiscount());
        order.setBasket(basketService.getBasketById(basketId));
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        return orderRepository.save(order);
    }

    public Client addOrUpdateClient(Order order, long basketId) {
        if (clientService.getClientByEmail(order.getClient().getEmail()) != null) {
            order.getClient().setId(clientService.getClientByEmail(order.getClient().getEmail()).getId());
            order.getClient().getAddress().setId(clientService.getClientByEmail(order.getClient().getEmail()).getAddress().getId());
            return clientService.createOrUpdateClient(order.getClient());
        }
        return clientService.createOrUpdateClient(order.getClient());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrderByDate(Date date) {
        return orderRepository.getOrderByDate(date);
    }

    public List<Order> getOrderBySize(Integer min, Integer max) {
        return orderRepository.getOrderBySize(min, max);
    }

    public List<Order> getOrderByClientId(Long id) {
        return orderRepository.getOrderByClientId(id);
    }
}
