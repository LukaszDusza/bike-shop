package pl.akademia.api.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.client.Client;
import pl.akademia.api.client.ClientService;

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
    public ResponseEntity<List<Order>> getOrders(@RequestParam(required=false) Integer min, @RequestParam(required = false) Integer max){
        List<Order> orders;
        if(min==null && max==null){
            orders=orderService.getAllOrders();
            if (orders.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }else{
            orders= orderService.getOrderBySize(min,max);
            if (orders.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("/orders/client/{id}")
    public ResponseEntity<List<Order>> getOrderByClientId(@PathVariable Long id){
        List<Order> orders= orderService.getOrderByClientId(id);
        if (orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
