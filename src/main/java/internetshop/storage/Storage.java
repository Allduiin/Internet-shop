package internetshop.storage;

import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.model.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Long itemId = 0L;
    private static Long userId = 0L;
    private static Long bucketId = 0L;
    private static Long orderId = 0L;
    public static final List<Item> items = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();

    public static void addItem(Item item) {
        itemId++;
        item.setId(itemId);
        items.add(item);
    }

    public static void addItem(Item item) {
        itemId++;
        item.setId(itemId);
        items.add(item);
    }

    public static void addItem(Item item) {
        itemId++;
        item.setId(itemId);
        items.add(item);
    }

    public static void addOrder(Order order) {
        orderId++;
        order.setId(orderId);
        orders.add(order);
    }
}
