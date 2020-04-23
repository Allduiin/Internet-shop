package internetshop.dao.impl;

import internetshop.dao.BucketDao;
import internetshop.lib.Dao;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.storage.Storage;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.addBucket(bucket);
        return bucket;
    }

    @Override
    public Bucket addItem(Long bucketId, Item item) {
        getById(bucketId).get().addItem(item);
        return getById(bucketId).get();
    }


    @Override
    public Optional<Bucket> getById(Long id) {
        return Storage.buckets.stream()
                .filter(b -> b.getId() == id)
                .findFirst();
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }

    @Override
    public Bucket update(Bucket bucket) {
        IntStream.range(0, Storage.buckets.size())
                .filter(x -> bucket.getId() == Storage.buckets.get(x).getId())
                .forEach(b -> Storage.items.set(b, item));
        return item;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
