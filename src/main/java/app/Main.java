package app;

import app.configuration.HibernateConfiguration;
import app.controller.gui.LoginController;
import app.controller.rest.ClientController;
import app.model.*;
import app.repository.implemetation.OrderRepositoryImpl;
import app.service.*;
import app.single_point_access.ServiceSinglePointAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@SpringBootApplication
public class Main {

    private static final String APPLICATION_CONFIGURATION_FILE = "app.configuration.properties";

    public static void main(String[] args) {

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(APPLICATION_CONFIGURATION_FILE)) {
            Properties properties = new Properties();
            properties.load(input);

            // Decide app mode from file
            String mode = properties.getProperty("mode");

            if (mode.equals("web")) {
                SpringApplication.run(Main.class, args);
            } else {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        } catch (Exception ex) {
            System.out.println("Error at starting application...");
            ex.printStackTrace();
        }

        OrderService orderService = ServiceSinglePointAccess.getOrderService();
        ClientService clientService = ServiceSinglePointAccess.getClientService();
        RestaurantService restaurantService = ServiceSinglePointAccess.getRestaurantService();
        MenuService menuService = ServiceSinglePointAccess.getMenuService();
        DriverService driverService = ServiceSinglePointAccess.getDriverService();
        ProductService productService = ServiceSinglePointAccess.getProductService();

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Taverna Racilor");
        restaurant1.setPhone("0744478053");
        restaurant1.setOpeningHours("11:00 - 23:00");

        Address address1 = new Address();
        address1.setCity("Cluj-Napoca");
        address1.setStreet("Strada Alexandru Vaida Voevod");
        address1.setNumber(53);
        restaurant1.setAddress(address1);

        Menu menu1 = new Menu();

        List<Product> products1 = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductName("Calamar pane pe pat de cartofi");
        product1.setProductPrice(61.0);
        product1.setProductDescription("Calamar 250g, Cartofi 150g, Faina de grau alba 100g, Oua de gaina 50g, Ulei de floarea soarelui 20ml, Lămâie, Piper 1g, Sare 1g.");
//        productService.save(product1);
        products1.add(product1);

        Product product2 = new Product();
        product2.setProductName("Fish & Chips");
        product2.setProductPrice(61.0);
        product2.setProductDescription("File de cod 250g, Cartofi 150g, Faina de grau alba 100g, Oua de gaina 50g, Ulei de floarea soarelui 20ml, Lămâie, Piper 1g, Sare 1g.");
//        productService.save(product2);
        products1.add(product2);

        Product product3 = new Product();
        product3.setProductName("Carne de scoica pane");
        product3.setProductPrice(57.0);
        product3.setProductDescription("Carne de scoici 250g, Făină de grau alba 70g, Oua de gaina 50g, Ulei floarea soarelui 15ml, Lămâie, Lapte 5g.");
//        productService.save(product3);
        products1.add(product3);

        menu1.setProducts(products1);
        restaurant1.setMenu(menu1);
//        menuService.save(menu1);
//        restaurantService.save(restaurant1);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Big Belly");
        restaurant2.setPhone("0744478053");
        restaurant2.setOpeningHours("09:00 - 22:30");

        Address address2 = new Address();
        address2.setCity("Cluj-Napoca");
        address2.setStreet("Calea Manastur");
        address2.setNumber(68);
        restaurant2.setAddress(address2);

        Menu menu2 = new Menu();

        List<Product> products2 = new ArrayList<>();

        Product product4 = new Product();
        product4.setProductName("Shaorma la lipie mare");
        product4.setProductPrice(33.0);
        product4.setProductDescription("Piept de pui crocant, cartofi prăjiți, sos de castraveți, maioneză cu usturoi, salată iceberg, chili" + " 600 g");
//        productService.save(product4);
        products2.add(product4);

        Product product5 = new Product();
        product5.setProductName("Pizza Big Belly");
        product5.setProductPrice(40.0);
        product5.setProductDescription("Sos de roșii, porumb, șuncă, pui crocant, ciuperci, mozzarella, ardei gras " + "800 g");
//        productService.save(product5);
        products2.add(product5);

        Product product6 = new Product();
        product6.setProductName("Big Burger");
        product6.setProductPrice(42.0);
        product6.setProductDescription("Chiflă, 2x carne de vită, roșii, salată, bacon, castraveți murați, 2x cheddar, ceapă, sos burger, sos bbq 560 g");
//        productService.save(product6);
        products2.add(product6);

        menu2.setProducts(products2);
//        menuService.save(menu2);
        restaurant2.setMenu(menu2);
//        restaurantService.save(restaurant2);

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setName("Pizza Hut");
        restaurant3.setPhone("0744478963");
        restaurant3.setOpeningHours("10:00 - 22:00");

        Address address3 = new Address();
        address3.setCity("Cluj-Napoca");
        address3.setStreet("Strada Alexandru Vaida Voevod");
        address3.setNumber(53);
        restaurant3.setAddress(address3);

        Menu menu3 = new Menu();

        List<Product> products3 = new ArrayList<>();

        Product product7 = new Product();
        product7.setProductName("Pizza Pepperoni");
        product7.setProductPrice(45.0);
        product7.setProductDescription("Sos de roșii, mozzarella, pepperoni, bacon, ciuperci, ardei gras, ceapă, măsline negre, oregano " + "800 g");
//        productService.save(product7);
        products3.add(product7);

        Product product8 = new Product();
        product8.setProductName("Pizza Margherita");
        product8.setProductPrice(35.0);
        product8.setProductDescription("Sos de roșii, mozzarella, oregano " + "800 g");
//        productService.save(product8);
        products3.add(product8);

        Product product9 = new Product();
        product9.setProductName("Pizza Quattro Formaggi");
        product9.setProductPrice(45.0);
        product9.setProductDescription("Sos de roșii, mozzarella, gorgonzola, parmezan, emmentaler, oregano " + "800 g");
//        productService.save(product9);
        products3.add(product9);

        menu3.setProducts(products3);
//        menuService.save(menu3);
        restaurant3.setMenu(menu3);
//        restaurantService.save(restaurant3);

        Client client = new Client();
        client.setName("Rad Daniel");
        client.setPhone("0770578430");
        client.setPassword("1234");

        Address address4 = new Address();
        address4.setCity("Cluj-Napoca");
        address4.setStreet("Aleea Baita");
        address4.setNumber(2);

        client.setAddress(address4);
//        clientService.save(client);

        Client client1 = new Client();
        client1.setName("Pop Alexandru");
        client1.setPhone("0770576248");
        client1.setPassword("root");

        Address address5 = new Address();
        address5.setCity("Cluj-Napoca");
        address5.setStreet("Strada Bucuresti");
        address5.setNumber(5);

        client1.setAddress(address5);
//        clientService.save(client1);

        Client client2 = new Client();
        client2.setName("Bucur Andrei");
        client2.setPhone("0770572178");
        client2.setPassword("12345");

        Address address6 = new Address();
        address6.setCity("Cluj-Napoca");
        address6.setStreet("Strada Unirii");
        address6.setNumber(23);

        client2.setAddress(address6);
//        clientService.save(client2);

        System.out.println(menuService.findById(3));
        orderService.createOrder(clientService.findById(3), new java.util.Date(), menuService.findById(1), products1, 7);
    }
}
