package app.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import org.hibernate.annotations.Cascade;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name = "findAllMenus", query = "select m from Menu m")
        }
)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<Product> products;
}
