package internetshop.dao;

import internetshop.model.Order;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getUserOrders(Long userId);
}
