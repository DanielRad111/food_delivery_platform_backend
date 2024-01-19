package app.model;

import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p"),
                @NamedQuery(name = "findProductById", query = "SELECT p FROM Product p WHERE p.id = :id"),
                @NamedQuery(name = "findProductByName", query = "SELECT p FROM Product p WHERE p.productName = :productName")
        }
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String productName;
    @Column
    private String productDescription;
    @Column
    private Double productPrice;
}
