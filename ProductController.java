import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product-options")
    public String showProductOptions() {
        return "productOptions";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProductForm";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/product-options";
    }

    @GetMapping("/display-products")
    public String displayProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "displayProducts";
    }
}
