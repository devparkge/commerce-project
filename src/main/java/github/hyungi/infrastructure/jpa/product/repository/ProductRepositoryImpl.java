package github.hyungi.infrastructure.jpa.product.repository;

import github.hyungi.domain.product.model.Product;
import github.hyungi.domain.product.repository.ProductRepository;
import github.hyungi.infrastructure.jpa.product.dao.ProductDao;
import github.hyungi.infrastructure.jpa.product.entity.ProductEntity;
import github.hyungi.infrastructure.jpa.users.dao.UsersDao;
import github.hyungi.infrastructure.jpa.users.entity.UsersEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl extends ProductReadRepositoryImpl implements ProductRepository {
    private final ProductDao productDao;
    private final UsersDao usersDao;

    public ProductRepositoryImpl(ProductDao productDao, ProductDao productDao1, UsersDao usersDao) {
        super(productDao);
        this.productDao = productDao1;
        this.usersDao = usersDao;
    }

    @Override
    public Product save(Product product) {
        UsersEntity sellerEntity = usersDao.findById(product.seller().userId()).orElseThrow(IllegalArgumentException::new);
        return productDao.save(ProductEntity.from(product, sellerEntity)).toDomain();
    }
}
