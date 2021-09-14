package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Category;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.service.CatalogService;
import eu.opensource.ordermanagement.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/catalog")
@Controller
public class CatalogController {

    private final MessageSource messageSource;

    private Message message;

    private final CatalogService catalogService;

    private final Cart cart;

    @GetMapping("/{categoryId}")
    public String viewProductByCategory(@PathVariable Long categoryId, Model uiModel) {

        List<Category> categories = catalogService.getAllCategories();
        uiModel.addAttribute("categories", categories);

        Optional<Category> category = catalogService.getCategoryById(categoryId);
        String categoryName = null;
        if (category.isPresent()) {
            categoryName = category.get()
                                   .getName();
            uiModel.addAttribute("categoryName", categoryName);
        }

        List<Product> products = catalogService.getProductsByCategory(categoryId);
        uiModel.addAttribute("products", products);
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "catalog/productsList";
    }

    @GetMapping("/categories/{productId}")
    public String viewProductById(@PathVariable Long productId, Model uiModel, Locale locale) {

        Optional<Product> product = catalogService.getProductById(productId);
        if (product.isPresent()) {
            uiModel.addAttribute("product", product.get());
        } else {
            message = new Message("error", messageSource.getMessage("message.products.notexist", new Object[]{}, locale));
            uiModel.addAttribute("message", message);
        }

        return "catalog/productView";
    }
}
