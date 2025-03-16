package github.hyungi.domain.cart.model;

import github.hyungi.domain.user.model.User;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public record Cart(
        UUID cartId,
        User cartHost,
        List<Item> itemList,
        BigDecimal totalAmount,
        Instant createdAt,
        Instant updatedAt
) {
    public static Cart create(User cartHost, List<Item> itemList) {
        BigDecimal totalAmount = itemList.stream()
                .map(Item::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Cart.builder()
                .cartId(UUID.randomUUID())
                .cartHost(cartHost)
                .itemList(itemList)
                .totalAmount(totalAmount)
                .build();
    }

    public Cart updateItemList(Item item) {
        List<Item> newItemList = new ArrayList<>(itemList);
        newItemList.add(item);
        return Cart.builder()
                .cartId(cartId)
                .cartHost(cartHost)
                .itemList(newItemList)
                .totalAmount(totalAmount)
                .build();
    }
}
