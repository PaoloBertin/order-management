package eu.opensource.ordermanagement.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Table(name = "products")
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @EqualsAndHashCode.Include
    @NotNull
    @Column(name = "product_code")
    private String productCode;

    private BigDecimal price;

    @Size(max = 255)
    private String description;

    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "products_fk_01"))
    @ManyToOne
    private Category category;

    public Product() {

    }

    public Product(String name, String productCode) {

        this.name = name;
        this.productCode = productCode;
    }
}
