package app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name = "findDriverById", query = "select d from Driver d where d.id = :id"),
                @NamedQuery(name = "findFreeDriver", query = "select d from Driver d where d.driverStatus = 'FREE'"),
                @NamedQuery(name = "findAllDrivers", query = "select d from Driver d")
        }
)
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private DriverStatus driverStatus;

    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    @ToString.Exclude
    private List<Order> orders;
}
