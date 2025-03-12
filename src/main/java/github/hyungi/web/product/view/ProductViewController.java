package github.hyungi.web.product.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    @GetMapping("/product-add")
    public String productAdd() {
        return "product/product_add";
    }
}
