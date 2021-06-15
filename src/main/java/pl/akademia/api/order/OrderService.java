package pl.akademia.api.order;

import org.springframework.stereotype.Service;
import pl.akademia.api.bike.Bike;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order getOrderById(Long id){return orderRepository.findById(id).orElse(null);}

    public List<Order> getOrderByDate(Date date){return orderRepository.getOrderByDate(date);}

//    public Order createOrder(Order order) {
//        return orderRepository.save(order);
//    }

    public List<Order> getOrderByClientId(Long id) {

        return orderRepository.getOrderByClientId(id);
        }

        public List<Order> getOrderByBikeBrand(String brand) {

        return orderRepository.getOrderByBikeBrand(brand);
        }
}
