package app.repository;

import app.model.*;

import java.math.BigDecimal;
import java.util.*;

public interface OrderRepository extends CRUDRepository<Order, Integer>{
    void updateOrderStatus(Integer id, OrderStatus newStatus);
    List<Order> findOrdersByClient(Client client);
    List<Order> findOrdersByDateRange(Date startDate, Date endDate);
    List<Order> getOrdersHistory(Client client);
    Double getTotalPrice(Order order);
//    void createOrderAndAssignDriver(Order order, Date date);
}
