package internetshop.service;

import internetshop.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item create(Item item);

    Optional<Item> getById(Long id);

    List<Item> getAll();

    Item update(Item item);

    boolean delete(Long id);
}
