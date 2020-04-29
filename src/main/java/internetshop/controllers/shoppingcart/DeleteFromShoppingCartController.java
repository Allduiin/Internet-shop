package internetshop.controllers.shoppingcart;

import internetshop.lib.Injector;
import internetshop.service.ProductService;
import internetshop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFromShoppingCartController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    ShoppingCartService shoppingCartService = (ShoppingCartService)
            INJECTOR.getInstance(ShoppingCartService.class);
    ProductService productService = (ProductService)
            INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        shoppingCartService.deleteProduct(shoppingCartService
                .getById(USER_ID),productService.getById(id));
        resp.sendRedirect(req.getContextPath() + "/shoppingCart/get");
    }
}
