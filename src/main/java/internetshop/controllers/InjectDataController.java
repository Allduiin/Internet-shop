package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.model.User;
import internetshop.service.ProductService;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    UserService userService = (UserService) INJECTOR.getInstance(UserService.class);
    ProductService productService = (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User alisa = new User("alisa","123412e");
        User vasya = new User("Vasya","7yu32r");
        userService.create(alisa);
        userService.create(vasya);
        Product bread = new Product("alisa",5);
        Product computer = new Product("Vasya",1000);
        productService.create(bread);
        productService.create(computer);

        req.getRequestDispatcher("/WEB-INF/views/injectData.jsp").forward(req, resp);
    }
}
