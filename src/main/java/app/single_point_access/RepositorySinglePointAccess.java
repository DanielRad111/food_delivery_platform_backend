package app.single_point_access;

import app.repository.*;
import app.repository.implemetation.*;
import org.hibernate.SessionFactory;

public class RepositorySinglePointAccess {
    private SessionFactory sessionFactory;
    private static ClientRepository clientRepository;
    private static OrderRepository orderRepository;
    private static DriverRepository driverRepository;
    private static MenuRepository menuRepository;
    private static RestaurantRepository restaurantRepository;

    private static ProductRepository productRepository;

    static {
        clientRepository = new ClientRepositoryImpl();
        orderRepository = new OrderRepositoryImpl();
        driverRepository = new DriverRepositoryImpl();
        restaurantRepository = new RestaurantRepositoryImpl();
        menuRepository = new MenuRepositoryImpl();
        productRepository = new ProductRepositoryImpl();
    }


    public static ClientRepository getUserRepository() {
        return clientRepository;
    }

    public static OrderRepository getOrderRepository() {return orderRepository;}

    public static DriverRepository getDriverRepository() {return driverRepository;}

    public static RestaurantRepository getRestaurantRepository() {return restaurantRepository;}

    public static MenuRepository getMenuRepository() {return menuRepository;}

    public static ProductRepository getProductRepository() {return productRepository;}
}
