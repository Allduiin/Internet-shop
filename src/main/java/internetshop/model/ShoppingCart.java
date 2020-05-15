package internetshop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private Long id;
    private Long userId;
    private List<Product> products;

    public ShoppingCart(Long userId) {
        this.userId = userId;
        products = new ArrayList<>();
    }

    public ShoppingCart(Long id, Long userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public ShoppingCart(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long id) {
        this.userId = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addItem(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "ShoppingCart{"
                + "id=" + id
                + ", userId=" + userId
                + ", products=" + products
                + '}';
    }
}
