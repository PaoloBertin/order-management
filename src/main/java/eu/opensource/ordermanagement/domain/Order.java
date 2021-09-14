package eu.opensource.ordermanagement.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_id_01"))
    @ManyToOne
    private Customer customer;

    @JoinTable(name = "order_line_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "line_item_id"))
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LineItem> lineItems = new ArrayList<>();


}
