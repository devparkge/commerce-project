package github.hyungi.web.cart.view;

import github.hyungi.annotation.JwtAuthentication;
import github.hyungi.domain.cart.model.Cart;
import github.hyungi.domain.cart.service.GetCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartViewController {
    private final GetCartService getCartService;

    @GetMapping
    public String cartList(
            @JwtAuthentication UUID authUserUUID,
            Model model
    ) {
        Cart cart = getCartService.getCart(authUserUUID);
        model.addAttribute("cart", cart);
        return "cart/cart_list";
    }
}
