package internetshop.dao;

import internetshop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {
    Optional<ShoppingCart> getByUserId(Long userId);

    Optional<ShoppingCart> get(Long id);

    ShoppingCart update(ShoppingCart shoppingCart);

    List<ShoppingCart> getAllShoppingCarts();

    ShoppingCart create(ShoppingCart shoppingCart);
}
