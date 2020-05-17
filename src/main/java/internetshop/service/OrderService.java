package internetshop.service;

import internetshop.model.Order;
import internetshop.model.Product;
import java.util.List;

public interface OrderService extends GenericService<Order> {
    Order completeOrder(List<Product> products, Long userId);

    List<Order> getUserOrders(Long userId);
}
