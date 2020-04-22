package internetshop.dao.impl;

import internetshop.dao.ItemDao;
import internetshop.model.Item;
import internetshop.storage.Storage;

import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {

    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
