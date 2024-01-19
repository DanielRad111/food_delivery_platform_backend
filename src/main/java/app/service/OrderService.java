package app.service;

import app.model.*;

import java.math.BigDecimal;
import java.util.*;

public interface OrderService {
    Order save(Order order);
    Order findById(Integer id);
    List<Order> findAll();
    void updateOrderStatus(Integer id, OrderStatus newStatus);
    void update(Order order);
    boolean delete(Order order);
    List<Order> findOrdersByClient(Client client);
    List<Order> findOrdersByDateRange(Date startDate, Date endDate);
    List<Order> getOrderHistory(Client client);
    Double getTotalPrice(Order order);
    void createOrder(Client client, Date date, Menu menu, List<Product> products, Integer quantity);
    void deliverOrder(Order order);
}
