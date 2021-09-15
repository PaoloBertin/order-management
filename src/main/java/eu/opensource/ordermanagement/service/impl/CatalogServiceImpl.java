package eu.opensource.ordermanagement.service.impl;

import eu.opensource.ordermanagement.domain.Category;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.repository.CategoryRepository;
import eu.opensource.ordermanagement.repository.ProductRepository;
import eu.opensource.ordermanagement.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProductById(Long productId) {

        return productRepository.findById(productId);
    }

    @Override
    public Product getProductByCode(String productCode) {

        return productRepository.findByProductCode(productCode);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {

        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {

        productRepository.delete(product);
    }

    @Override
    public Optional<Category> getCategoryById(Long categoryId) {

        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category getCategoryByName(String categoryName) {

        return categoryRepository.findByName(categoryName);
    }

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }
}
