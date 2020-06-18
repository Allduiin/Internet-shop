package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.Role;
import internetshop.model.ShoppingCart;
import internetshop.model.User;
import internetshop.service.OrderService;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/injectData")
public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private static final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product bread = new Product("bread",5);
        Product computer = new Product("computer",1000);
        productService.create(bread);
        productService.create(computer);
        User admin = userService.create(new User("Admin","admin"));
        Role role = new Role(Role.RoleName.ADMIN);
        role.setId(1L);
        admin.setRoles(List.of(new Role(Role.RoleName.ADMIN)));
        userService.update(admin);
        Long userId1 = userService.create(new User("alisa","123412e")).getId();
        Long userId2 = userService.create(new User("Vasya","7yu32r")).getId();
        ShoppingCart shoppingCart =
                shoppingCartService.create(new ShoppingCart(userId1));
        ShoppingCart shoppingCart2 =
                shoppingCartService.create(new ShoppingCart(userId2));
        shoppingCartService.addProduct(shoppingCart, bread);
        shoppingCartService.addProduct(shoppingCart, computer);
        shoppingCartService.addProduct(shoppingCart, bread);
        shoppingCartService.addProduct(shoppingCart2, computer);
        shoppingCartService.addProduct(shoppingCart2, bread);
        shoppingCartService.addProduct(shoppingCart2, computer);
        orderService.completeOrder(shoppingCart2.getProducts(),shoppingCart2.getUserId());

        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
