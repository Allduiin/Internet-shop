package internetshop.storage;

import internetshop.model.ShoppingCart;
import internetshop.model.Product;
import internetshop.model.Order;
import internetshop.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static final List<Product> products = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    private static Long orderId = 0L;
    private static Long itemId = 0L;
    private static Long userId = 0L;
    private static Long bucketId = 0L;

    public static void addProduct(Product product) {
        itemId++;
        product.setId(itemId);
        products.add(product);
    }

    public static void addUser(User user) {
        userId++;
        user.setId(userId);
        users.add(user);
    }

    public static void addShoppingCart(ShoppingCart shoppingCart) {
        bucketId++;
        shoppingCart.setId(bucketId);
        shoppingCarts.add(shoppingCart);
    }

    public static void addOrder(Order order) {
        orderId++;
        order.setId(orderId);
        orders.add(order);
    }
}
