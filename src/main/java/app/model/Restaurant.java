package app.model;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name = "findRestaurantByName", query = "select r from Restaurant r where r.name = :name"),
                @NamedQuery(name = "findRestaurantById", query = "select r from Restaurant r where r.id = :id"),
                @NamedQuery(name = "findAllRestaurants", query = "select r from Restaurant r")
        }
)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column
    private String phone;

    @Column
    private String openingHours;
}
