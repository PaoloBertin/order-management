package eu.opensource.ordermanagement.repository;

import eu.opensource.ordermanagement.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    Product findByProductCode(String productCode);
}
