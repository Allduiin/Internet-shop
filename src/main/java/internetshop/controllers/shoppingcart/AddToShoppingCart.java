package internetshop.controllers.shoppingcart;

import internetshop.lib.Injector;
import internetshop.model.ShoppingCart;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/shoppingCart/add")
public class AddToShoppingCart extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        ShoppingCart shoppingCart = shoppingCartService
                .getByUserId((Long) req.getSession().getAttribute(USER_ID));
        shoppingCartService.addProduct(shoppingCart, productService.getById(productId));

        req.setAttribute("message", productService.getById(productId)
                .getName() + " was added to shopping cart");
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/products/all.jsp").forward(req, resp);
    }
}
