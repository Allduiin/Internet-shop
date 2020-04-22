package internetshop.dao;

import internetshop.model.Bucket;
import internetshop.model.Item;
import java.util.List;
import java.util.Optional;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket addItem(Long bucketId, Item item);

    Optional<Bucket> getById(Long id);

    List<Bucket> getAll();

    Bucket update(Bucket bucket);

    boolean delete(Long id);
}
