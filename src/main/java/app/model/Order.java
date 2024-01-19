package app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "`Order`")
@Data
@NamedQueries(
        {
                @NamedQuery(name = "findOrderById", query = "select o from Order o where o.id = :id"),
                @NamedQuery(name = "findAllOrders", query = "select o from Order o"),
                @NamedQuery(name = "findOrdersByClient", query = "select o from Order o where o.client = :client"),
                @NamedQuery(name = "findOrdersByDateRange", query = "select o from Order o where o.orderDateTime between :startDate and :endDate")
        }
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    @JsonIgnore
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<Product> products;

    @Column
    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date orderDateTime;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;
}
