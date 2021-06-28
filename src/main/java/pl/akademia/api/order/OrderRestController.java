package pl.akademia.api.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.exceptions.OrderNotFoundException;
import pl.akademia.api.exceptions.RequestBodyHasNullFieldException;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private final OrderService orderService;
    public static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        List<Order> orders;
        if (min == null && max == null) {
            orders = orderService.getAllOrders();
            if (orders.isEmpty()) {
                logger.info("OrderNotFoundException: BikeShop has no any orders yet.");
                throw new OrderNotFoundException("BikeShop has no any orders yet.");
            }
        } else {
            orders = orderService.getOrderBySize(min, max);
            if (orders.isEmpty()) {
                logger.info("OrderNotFoundException: Orders not found for the range: {} - {}.", min, max);
                throw new OrderNotFoundException("Orders not found for the");
            }
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            logger.info("Order not found by id = {}.", id);
            throw new OrderNotFoundException("Order not found by id = " + id);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/orders/date/{date}")
    public ResponseEntity<List<Order>> getOrderByDate(@PathVariable Date date) {
        List<Order> orders = orderService.getOrderByDate(date);
        if (orders.isEmpty()) {
            logger.info("Order not found for date: {}.", date);
            throw new OrderNotFoundException("Orders not found for date: " + date);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> creatOrder(@RequestBody Order order,
                                            @RequestParam long basketId,
                                            @RequestParam(required = false) String promoCode) {

        if(order.anyFieldIsNull()) {
            logger.error("Exception: Order has null field");
            throw new RequestBodyHasNullFieldException("Order has null field");
        }
        if (promoCode == null) {
            return new ResponseEntity<>(orderService.createOrder(order, basketId), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(orderService.createOrder(order, basketId, promoCode), HttpStatus.CREATED);
    }

    @GetMapping("/orders/client/{id}")
    public ResponseEntity<List<Order>> getOrderByClientId(@PathVariable Long id) {
        List<Order> orders = orderService.getOrderByClientId(id);
        if (orders.isEmpty()) {
            logger.info("Client has no any orders yet. Client id = {}.", id);
            throw new OrderNotFoundException("Client has no any orders yet. Client id = " + id);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
