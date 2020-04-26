package internetshop.service;

import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartService extends GenericService<ShoppingCart> {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart getByUserId(Long userId);

    List<Product> getAllProducts(ShoppingCart shoppingCart);

    void clear(ShoppingCart shoppingCart);

    boolean deleteProduct(ShoppingCart shoppingCart, Product product);
}
