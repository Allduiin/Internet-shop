package internetshop.dao.impl;

import internetshop.dao.ShoppingCartDao;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.storage.Storage;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getUser().getId().equals(userId))
                .findFirst();
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(x -> shoppingCart.getId().equals(Storage.shoppingCarts.get(x).getId()))
                .forEach(s -> Storage.shoppingCarts.set(s, shoppingCart));
        return shoppingCart;
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }
}
