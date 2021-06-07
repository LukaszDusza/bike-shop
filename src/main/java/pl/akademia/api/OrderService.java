package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.Order;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){return orderRepository.findAll();}

    public Order getOrderById(Long id){return orderRepository.findById(id).orElse(null);}

    public List<Order> getOrderByDate(Date date){return orderRepository.getOrderByDate(date);}

    public List<Order> getOrderBySize(int min, int max){return orderRepository.getOrderBySize(min, max);}
}
