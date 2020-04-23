package internetshop.service;

import internetshop.model.Item;
import java.util.List;

public interface ItemService {
    Item create(Item item);

    Item getById(Long id);

    List<Item> getAll();

    Item update(Item item);

    boolean delete(Long id);
}
