package internetshop.controllers.shoppingcart;

import internetshop.lib.Injector;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/shoppingCart/deleteProduct")
public class DeleteFromShoppingCartController extends HttpServlet {
    private static final String USER_ID = "user_id";
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final ShoppingCartService shoppingCartService = (ShoppingCartService)
            INJECTOR.getInstance(ShoppingCartService.class);
    private static final ProductService productService = (ProductService)
            INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        shoppingCartService.deleteProduct(shoppingCartService
                .getByUserId((Long) req.getSession().getAttribute(USER_ID)),
                productService.getById(id));
        resp.sendRedirect(req.getContextPath() + "/shoppingCart/get");
    }
}
