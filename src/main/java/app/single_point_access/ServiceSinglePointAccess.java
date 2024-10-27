package app.single_point_access;

import app.repository.MenuRepository;
import app.service.*;
import app.service.implementation.*;

public class ServiceSinglePointAccess {

    private static ClientService clientService;
    private static OrderService orderService;
    private static DriverService driverService;
    private static MenuService menuService;
    private static RestaurantService restaurantService;
    private static ProductService productService;

    static {
        clientService = new ClientServiceImpl();
        orderService = new OrderServiceImpl();
        driverService = new DriverServiceImpl();
        restaurantService = new RestaurantServiceImpl();
        menuService = new MenuServiceImpl();
        productService = new ProductServiceImpl();
    }

    public static ClientService getClientService() {
        return clientService;
    }

    public static OrderService getOrderService() {return orderService;}

    public static DriverService getDriverService() {return driverService;}

    public static RestaurantService getRestaurantService() {return restaurantService;}

    public static MenuService getMenuService() {return menuService;}

    public static ProductService getProductService() {return productService;}
}
