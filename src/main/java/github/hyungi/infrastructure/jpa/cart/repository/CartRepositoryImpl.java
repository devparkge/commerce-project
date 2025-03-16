package github.hyungi.infrastructure.jpa.cart.repository;

import github.hyungi.domain.cart.model.Cart;
import github.hyungi.domain.cart.model.Item;
import github.hyungi.domain.cart.repository.CartRepository;
import github.hyungi.infrastructure.jpa.cart.dao.CartDao;
import github.hyungi.infrastructure.jpa.cart.dao.ItemDao;
import github.hyungi.infrastructure.jpa.cart.entity.CartEntity;
import github.hyungi.infrastructure.jpa.cart.entity.ItemEntity;
import github.hyungi.infrastructure.jpa.product.dao.ProductDao;
import github.hyungi.infrastructure.jpa.product.entity.ProductEntity;
import github.hyungi.infrastructure.jpa.users.dao.UsersDao;
import github.hyungi.infrastructure.jpa.users.entity.UsersEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@Repository
public class CartRepositoryImpl extends CartReadRepositoryImpl implements CartRepository {
    private final UsersDao usersDao;
    private final ItemDao itemDao;
    private final CartDao cartDao;
    private final ProductDao productDao;

    public CartRepositoryImpl(CartDao cartDao, UsersDao usersDao, ItemDao itemDao, CartDao cartDao1, ProductDao productDao) {
        super(cartDao);
        this.usersDao = usersDao;
        this.itemDao = itemDao;
        this.cartDao = cartDao1;
        this.productDao = productDao;
    }

    @Override
    public Cart save(Cart cart) {
        UsersEntity cartHostEntity = usersDao.findById(cart.cartHost().userId()).get();
        return cartDao.save(CartEntity.from(
                cart,
                cartHostEntity,
                getItems(cart)
        )).toDomain();
    }

    private List<ItemEntity> getItems(Cart cart) {
        List<ItemEntity> items = itemDao.findAllById(cart.itemList().stream().map(Item::itemId).toList());

        if(!items.stream().isParallel()) {
            return cart.itemList().stream()
                    .map(item -> {
                        ProductEntity productEntity = productDao.findById(item.product().productId()).get();
                        return ItemEntity.from(item, productEntity);
                    }).toList();
        }

        List<UUID> dbItemIds = items.stream()
                .map(ItemEntity::getItemId)

                .toList();
        List<ItemEntity> newItems = cart.itemList().stream()
                .filter(item -> !dbItemIds.contains(item.itemId()))
                .map(item -> {
                    ProductEntity productEntity = productDao.findById(item.product().productId()).get();
                    return ItemEntity.from(item, productEntity);
                }).toList();

        return Stream.concat(
                items.stream(),
                newItems.stream()
        ).toList();
    }
}
