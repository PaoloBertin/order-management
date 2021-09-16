package eu.opensource.ordermanagement.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Table(name = "line_items")
@Entity
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "line_items_fk_01"))
    private Product product;

    @Transient
    private BigDecimal price;

    private Integer quantity;

    @Transient
    private BigDecimal quantityBig;

    public BigDecimal getPrice() {

        return product.getPrice();
    }

    public BigDecimal getQuantityBig() {

        return BigDecimal.valueOf(quantity);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return product.getProductCode()
                      .equals(lineItem.product.getProductCode());
    }

    @Override
    public int hashCode() {

        return Objects.hash(product.getProductCode());
    }
}
