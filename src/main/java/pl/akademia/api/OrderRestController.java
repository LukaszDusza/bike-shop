package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademia.api.model.Order;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders=orderService.getAllOrders();
        if (orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order= orderService.getOrderById(id);
        if (order==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @GetMapping("/orders/date/{date}")
    public ResponseEntity<List<Order>> getOrderByDate(@PathVariable Date date){
        List<Order> orders= orderService.getOrderByDate(date);
        if (orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/orders/size/{min}-{max}")
    public ResponseEntity<List<Order>> getOrderBySize(@PathVariable int min,@PathVariable int max){
        List<Order> orders= orderService.getOrderBySize(min,max);
        if (orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
