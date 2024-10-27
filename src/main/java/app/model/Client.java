package app.model;
import lombok.*;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;
@Entity
@Table
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findClientByName", query = "from Client c where c.name = :name"),
        @NamedQuery(name = "findClientByNameAndPassword", query = "from Client c where c.name = :name and c.password = :password"),
        @NamedQuery(name = "findClientById", query = "from Client c where c.id = :id"),
        @NamedQuery(name = "findAllClients", query = "from Client")
})
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Order> orders;
}
