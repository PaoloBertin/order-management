package eu.opensource.ordermanagement.service;

import eu.opensource.ordermanagement.domain.Category;
import eu.opensource.ordermanagement.domain.Product;

import java.util.List;
import java.util.Optional;

public interface CatalogService {

    Optional<Product> getProductById(Long id);

    Product getProductByCode(String productCode);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Product product);

    Optional<Category> getCategoryById(Long categoryId);

    Category getCategoryByName(String categoryName);

    List<Category> getAllCategories();
}
