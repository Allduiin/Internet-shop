package internetshop.model;

import java.util.List;

public class Order {
    private User user;
    private List<Item> items;
    private long id;

    public Order(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
