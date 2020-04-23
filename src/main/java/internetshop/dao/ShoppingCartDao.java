package internetshop.dao;

import internetshop.model.ShoppingCart;
import internetshop.model.Product;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);
    boolean deleteProduct(ShoppingCart shoppingCart, Product product);
    void clear(ShoppingCart shoppingCart); //remove all products from the shoppingCart
    ShoppingCart getByUserId(Long userId);
    List<Product> getAllProducts(ShoppingCart shoppingCart);
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addItem(Long bucketId, Product product);

    Optional<ShoppingCart> getById(Long id);

    List<ShoppingCart> getAll();

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean delete(Long id);
}
