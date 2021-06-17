package pl.akademia.api.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademia.api.exceptions.OrderNotFoundByException;
import pl.akademia.api.exceptions.OrderNotFoundException;

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
    public ResponseEntity<List<Order>> getOrders(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        List<Order> orders;
        if (min == null && max == null) {
            orders = orderService.getAllOrders();
            if (orders.isEmpty()) {
                throw new OrderNotFoundException("BikeShop has no any orders yet.");
            }
        } else {
            orders = orderService.getOrderBySize(min, max);
            if (orders.isEmpty()) {
                throw new OrderNotFoundException("Orders not found for the");
            }
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new OrderNotFoundByException("Order not found by id = " + id); //czy umieszczenie zmiennej id w tym miejscu jest prawidłowym podejściem czy też powinienem umieścić ją w RestExceptionHandler w metodzie jako request.getParameter?
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/orders/date/{date}")
    public ResponseEntity<List<Order>> getOrderByDate(@PathVariable Date date) {
        List<Order> orders = orderService.getOrderByDate(date);
        if (orders.isEmpty()) {
            throw new OrderNotFoundByException("Orders not found for date: " + date);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> creatOrder(@RequestBody Order order,
                                            @RequestParam long basketId,
                                            @RequestParam(required = false) String promoCode) {
        if (promoCode == null) {
            return new ResponseEntity<>(orderService.createOrder(order, basketId), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(orderService.createOrder(order, basketId, promoCode), HttpStatus.CREATED);
    }

    @GetMapping("/orders/client/{id}")
    public ResponseEntity<List<Order>> getOrderByClientId(@PathVariable Long id) { //czy w ramach tej metody próbować łapać błąd związany z brakiem klienta w DB? Obecnie po podaniu id, które nie istnieje wyświetla: "Client has no any orders yet. Client id = " + id.
        List<Order> orders = orderService.getOrderByClientId(id);
        if (orders.isEmpty()) {
            throw new OrderNotFoundByException("Client has no any orders yet. Client id = " + id);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
