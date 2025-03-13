package github.hyungi.web.model;

import github.hyungi.domain.user.model.User;

public record Seller(
        String sellerName,
        String phoneNumber
) {
    public static Seller from(User seller) {
        return new Seller(
                seller.username(),
                seller.phoneNumber()
        );
    }
}
