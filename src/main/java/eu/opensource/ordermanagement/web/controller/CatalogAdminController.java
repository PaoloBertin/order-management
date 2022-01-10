package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Category;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.service.CatalogService;
import eu.opensource.ordermanagement.web.util.Message;
import eu.opensource.ordermanagement.web.util.ProductForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/catalog")
@Controller
public class CatalogAdminController {

    private final CatalogService catalogService;

    private final MessageSource messageSource;

    private final Cart cart;

    @GetMapping("/categories/products")
    public String viewCreateProductForm(@RequestParam String form,
            Model uiModel) {

        List<Category> categories = catalogService.getAllCategories();
        uiModel.addAttribute("categories", categories);

        ProductForm productForm = new ProductForm();
        uiModel.addAttribute("productForm", productForm);
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "catalog/createProduct";
    }

    @PostMapping("/categories/products")
    public String createProduct(@Valid ProductForm productForm,
            BindingResult result,
            Model uiModel,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        // verifica che i dati del form siano validi
        Message message = null;
        if (result.hasErrors()) {
            message = new Message("error",
                    messageSource.getMessage("message.product_save_fail", new Object[] {}, locale));
            uiModel.addAttribute("message", message);
            return "catalog/createProduct";
        }

        Category category = catalogService.getCategoryByName(productForm.getCategoryName());
        Product product = new Product();
        product.setName(productForm.getName());
        product.setProductCode(productForm.getProductCode());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setCategory(category);

        log.debug("Product: {}", product);

        // rende persistenti dati prodotto
        product = catalogService.saveProduct(product);

        message = new Message("success",
                messageSource.getMessage("message.product_save_success", new Object[] {}, locale));
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/";
    }

    @GetMapping("/{categoryId}/products")
    public String viewProductsByCategory(@PathVariable Long categoryId, Model uiModel) {

        List<Category> categories = catalogService.getAllCategories();
        List<Product> products = catalogService.getProductsByCategory(categoryId);

        Optional<Category> category = catalogService.getCategoryById(categoryId);
        if (category.isPresent()) {
            String categoryName = catalogService.getCategoryByName(category.get()
                    .getName())
                    .getName();
            uiModel.addAttribute("categoryName", categoryName);
        }

        uiModel.addAttribute("categories", categories);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "catalog/productsListAdmin";
    }

    @GetMapping("/categories/{productId}")
    public String viewEditProductForm(@PathVariable Long productId, Model uiModel) {

        ProductForm productForm = new ProductForm();
        Optional<Product> optional = catalogService.getProductById(productId);
        if (optional.isPresent()) {
            Product product = optional.get();
            productForm.setId(product.getId());
            productForm.setName(product.getName());
            productForm.setProductCode(product.getProductCode());
            productForm.setPrice(product.getPrice());
            productForm.setDescription(product.getDescription());
            productForm.setCategoryName(product.getCategory()
                    .getName());
            uiModel.addAttribute("productForm", productForm);
            List<Category> categories = catalogService.getAllCategories();
            uiModel.addAttribute("categories", categories);
            uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());
            return "catalog/editProduct";
        } else {
            return "catalog/productsListAdmin";
        }
    }

    @PutMapping("/categories/products")
    public String editProduct(@Valid ProductForm productForm,
                              BindingResult result,
                              Model uiModel,
                              RedirectAttributes redirectAttributes,
                              Locale locale) {

        // verifica che i dati del form siano validi
        Message message = null;
        if (result.hasErrors()) {
            message = new Message("error", messageSource.getMessage("message.product_save_fail", new Object[] {}, locale));
            uiModel.addAttribute("message", message);
            return "catalog/productListAdmin";
        }

        Category category = catalogService.getCategoryByName(productForm.getCategoryName());
        Product product = new Product();
        product.setId(productForm.getId());
        product.setName(productForm.getName());
        product.setProductCode(productForm.getProductCode());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setCategory(category);

        log.info("Product: {}", product);

        // rende persistenti dati prodotto
        product = catalogService.saveProduct(product);

        message = new Message("success",
                messageSource.getMessage("message.product_save_success", new Object[] {}, locale));
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/";
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam Long id) {

        Product product = catalogService.getProductById(id)
                .orElseThrow();

        catalogService.deleteProduct(product);

        return "redirect:/";
    }

}
