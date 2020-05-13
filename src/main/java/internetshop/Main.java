package internetshop;

import internetshop.lib.Injector;
import internetshop.model.Order;
import internetshop.model.Product;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.OrderService;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        UserService userService = (UserService) injector.getInstance(UserService.class);
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        initializeDb(productService, userService, shoppingCartService, orderService);

        List<Product> allProducts = productService.getAll();
        for (Product product : allProducts) {
            System.out.println(product.toString());
        }

        List<User> allUsers = userService.getAll();
        for (User user : allUsers) {
            System.out.println(user.toString());
        }

        List<ShoppingCart> allShoppingCarts = shoppingCartService.getAll();
        for (ShoppingCart shoppingCart : allShoppingCarts) {
            System.out.println(shoppingCart.toString());
        }

        List<Order> allOrders = orderService.getAll();
        for (Order order : allOrders) {
            System.out.println(order.toString());
        }
    }

    private static void initializeDb(ProductService productService, UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     OrderService orderService) {
        Product product1 = new Product("iPhone", 1000);
        Product product2 = new Product("Nokia", 100);
        Product product3 = new Product("Sumsung", 500);
        Product productUpdate = new Product(1L,"Vasa", 1000);
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        productService.update(productUpdate);
        productService.delete(1L);
        User user1 = new User("Pasha", "15fe32d5f");
        User user2 = new User("Vasia", "8734t");
        User user3 = new User("Petia", "df15");
        user1 = userService.create(user1);
        user2 = userService.create(user2);
        user3 = userService.create(user3);
        User userUpdate = new User(user2.getId(),"Vasa", "petya");
        userService.update(userUpdate);
        userService.delete(user1.getId());
        ShoppingCart shoppingCart = new ShoppingCart(user3.getId());
        shoppingCart = shoppingCartService.create(shoppingCart);
        shoppingCartService.addProduct(shoppingCart, product1);
        shoppingCartService.addProduct(shoppingCart, product2);
        shoppingCartService.addProduct(shoppingCart, product2);
        shoppingCartService.update(shoppingCart);
        ShoppingCart shoppingCart2 = new ShoppingCart(user2.getId());
        shoppingCart2 = shoppingCartService.create(shoppingCart2);
        shoppingCartService.addProduct(shoppingCart2, product3);
        shoppingCartService.addProduct(shoppingCart2, product3);
        shoppingCartService.addProduct(shoppingCart2, product3);
        shoppingCartService.update(shoppingCart2);
        orderService.completeOrder(shoppingCart2.getProducts(),shoppingCart.getUserId());
    }
}
