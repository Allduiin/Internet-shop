package internetshop.dao.impl;

import internetshop.dao.ProductDao;
import internetshop.lib.Dao;
import internetshop.model.Product;
import internetshop.storage.Storage;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addItem(product);
        return product;
    }

    @Override
    public Optional<Product> getById(Long id) {
        return Storage.PRODUCTS.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.PRODUCTS;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.PRODUCTS.size())
                .filter(x -> product.getId().equals(Storage.PRODUCTS.get(x).getId()))
                .forEach(i -> Storage.PRODUCTS.set(i, product));
        return product;
    }

    @Override
    public boolean delete(Long id) {
        if (id > 0 || id < Storage.PRODUCTS.size()) {
            Storage.PRODUCTS.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
