package app.service.implementation;

import app.model.*;
import app.repository.DriverRepository;
import app.repository.OrderRepository;
import app.service.OrderService;
import app.single_point_access.RepositorySinglePointAccess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository = RepositorySinglePointAccess.getOrderRepository();
    private DriverRepository driverRepository = RepositorySinglePointAccess.getDriverRepository();

    @Override
    public Order findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrderStatus(Integer id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id);
        if(order != null){
            order.setOrderStatus(newStatus);
        }
        orderRepository.update(order);
    }

    @Override
    public boolean delete(Order order) {
        return orderRepository.delete(order);
    }

    @Override
    public List<Order> findOrdersByClient(Client client) {
        return orderRepository.findOrdersByClient(client);
    }

    @Override
    public List<Order> findOrdersByDateRange(Date startDate, Date endDate) {
        return orderRepository.findOrdersByDateRange(startDate, endDate);
    }

    @Override
    public List<Order> getOrderHistory(Client client) {
        return orderRepository.getOrdersHistory(client);
    }

    @Override
    public Double getTotalPrice(Order order) {
        return orderRepository.getTotalPrice(order);
    }

    @Override
    public Order save(Order order) {return orderRepository.save(order);}

    @Override
    public void update(Order order) {orderRepository.update(order);}

    @Override
    public void createOrder(Client client, Date date, Menu menu, List<Product> selectedProducts, Integer quantity) {
        Order order = new Order();
        order.setOrderDateTime(date);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setMenu(menu);
        order.setQuantity(quantity);

        order.setClient(client);

        order.setProducts(selectedProducts);

        confirmOrder(order);
        prepareOrder(order);

        List<Driver> freeDrivers = driverRepository.findFreeDrivers();
        if (!freeDrivers.isEmpty()) {
            Driver freeDriver = freeDrivers.get(0);

            assignDriver(order, freeDriver);
        } else {
            order.setOrderStatus(OrderStatus.CANCELED);
        }

        order.setOrderStatus(OrderStatus.DELIVERED);

        save(order);
    }



    private void confirmOrder(Order order){
        order.setOrderStatus(OrderStatus.CONFIRMED);
    }

    private void prepareOrder(Order order){
        order.setOrderStatus(OrderStatus.IN_PREPARATION);
    }

    private void assignDriver(Order order, Driver driver){
        order.setDriver(driver);
        driver.setDriverStatus(DriverStatus.BUSY);
    }

    @Override
    public void deliverOrder(Order order){
        order.setOrderStatus(OrderStatus.DELIVERED);
    }
}
