package pl.jablonskanycz.bakery.database.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ProductEntity {

    @Id
    @SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "productTypeId", referencedColumnName = "product_type_id")
    private ProductTypeEntity productType;

    @ManyToMany(mappedBy = "productsInThisOrder", fetch = FetchType.EAGER)
    private List<OrderEntity> orderWithProducts;
}
