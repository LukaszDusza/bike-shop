package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.order.Order;

@Controller
@RequestMapping("/api/v1")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> creatOrder(@RequestBody Order order,
                                            @RequestParam long basketId,
                                            @RequestParam(required = false) String promoCode){
        if (promoCode == null){
            return new ResponseEntity<>(orderService.creatOrder(order, basketId), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(orderService.creatOrder(order, basketId, promoCode), HttpStatus.CREATED);
    }
}
