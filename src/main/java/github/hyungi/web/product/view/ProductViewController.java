package github.hyungi.web.product.view;

import github.hyungi.domain.product.service.GetProductService;
import github.hyungi.web.product.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductViewController {
    private final GetProductService getProductService;

    @GetMapping
    public String productList(
            Model model
    ) {
        List<ProductResponse> productList = getProductService.getProducts().stream()
                .map(ProductResponse::from)
                .toList();
        model.addAttribute("products", productList);
        return "product/product_list";
    }

    @GetMapping("/{productId}")
    public String productDet(
            @PathVariable("productId") UUID productId,
            Model model
    ) {
        ProductResponse product = ProductResponse.from(getProductService.getProduct(productId));
        model.addAttribute("product", product);
        return "product/product_detail";
    }

    @GetMapping("/product-add")
    public String productAdd() {
        return "product/product_add";
    }
}
