package internetshop.dao.impl;

import internetshop.dao.ShoppingCartDao;
import internetshop.lib.Dao;
import internetshop.model.ShoppingCart;
import internetshop.model.Product;
import internetshop.storage.Storage;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.addItem(product);
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().remove(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {

    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addBucket(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart addItem(Long bucketId, Product product) {

    }


    @Override
    public Optional<ShoppingCart> getById(Long id) {
        return Storage.SHOPPING_CARTS.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.SHOPPING_CARTS;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.SHOPPING_CARTS.size())
                .filter(x -> shoppingCart.getId() == Storage.SHOPPING_CARTS.get(x).getId())
                .forEach(b -> Storage.SHOPPING_CARTS.set(b, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
