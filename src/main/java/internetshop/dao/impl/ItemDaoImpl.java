package internetshop.dao.impl;

import internetshop.dao.ItemDao;
import internetshop.lib.Dao;
import internetshop.model.Item;
import internetshop.storage.Storage;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.addItem(item);
        return item;
    }

    @Override
    public Optional<Item> getById(Long id) {
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
        IntStream.range(0, Storage.items.size())
                .filter(x -> item.getId().equals(Storage.items.get(x).getId()))
                .forEach(i -> Storage.items.set(i, item));
        return item;
    }

    @Override
    public boolean delete(Long id) {
        if (id > 0 || id < Storage.items.size()) {
            Storage.items.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
