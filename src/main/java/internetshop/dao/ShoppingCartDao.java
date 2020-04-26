package internetshop.dao;

import internetshop.model.ShoppingCart;
import java.util.Optional;

public interface ShoppingCartDao extends GenericDao<ShoppingCart> {
    Optional<ShoppingCart> getByUserId(Long userId);
}
