package eu.opensource.ordermanagement.web.util;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductForm {

    private Long id;

    private String name;

    @NotNull
    private String productCode;

    private BigDecimal price;

    @Size(max = 255)
    private String description;

    private String categoryName;
}
