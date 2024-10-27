package app.controller.rest;

import app.model.Order;
import app.model.OrderStatus;
import app.service.OrderService;
import app.single_point_access.ServiceSinglePointAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/order")
public class OrderController {

    private OrderService orderService = ServiceSinglePointAccess.getOrderService();

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.save(order));
    }

    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Integer id, @PathVariable OrderStatus status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order orderFromDB = orderService.findById(order.getId());
        orderFromDB.setOrderStatus(order.getOrderStatus());
        orderFromDB.setOrderDateTime(order.getOrderDateTime());
        orderFromDB.setClient(order.getClient());
        orderFromDB.setProducts(order.getProducts());
        orderFromDB.setQuantity(order.getQuantity());
        orderService.update(orderFromDB);
        return ResponseEntity.status(HttpStatus.OK).body(orderFromDB);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestBody Order order) {
        orderService.delete(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order with id " + order.getId() + " was deleted");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        orderService.delete(order);
        return ResponseEntity.status(HttpStatus.OK).body("Order with id " + order.getId() + " was deleted");
    }
}
