package internetshop.dao.impl;

import internetshop.dao.OrderDao;
import internetshop.lib.Dao;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.storage.Storage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Order orderClone = new Order(List.copyOf(order.getProducts()), order.getUser());
        Storage.addOrder(orderClone);
        return orderClone;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(o -> o.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(x -> order.getId().equals(Storage.orders.get(x).getId()))
                .forEach(o -> Storage.orders.set(o, order));
        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(order -> order.getId().equals(id));
    }
}
