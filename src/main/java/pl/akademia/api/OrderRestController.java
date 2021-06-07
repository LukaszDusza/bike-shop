package pl.akademia.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.akademia.api.model.Order;

@Controller
@RequestMapping("/api/v1")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders/{basketId}?promoCode")
    public ResponseEntity<Order> creatOrder(@RequestBody Order order,
                                            @PathVariable long basketId,
                                            @PathVariable(required = false) String promoCode){
        if (promoCode == null){
            return new ResponseEntity<>(orderService.creatOrder(order, basketId), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(orderService.creatOrder(order, basketId, promoCode), HttpStatus.CREATED);
    }
}
