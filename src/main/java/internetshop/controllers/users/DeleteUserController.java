package internetshop.controllers.users;

import internetshop.lib.Injector;
import internetshop.model.Order;
import internetshop.model.ShoppingCart;
import internetshop.service.OrderService;
import internetshop.service.ShoppingCartService;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/delete")
public class DeleteUserController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private static final ShoppingCartService shoppingCartService = (ShoppingCartService)
            INJECTOR.getInstance(ShoppingCartService.class);
    private static final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(id);
        shoppingCartService.delete(shoppingCart.getId());
        for (Order order: orderService.getUserOrders(id)) {
            orderService.delete(order.getId());
        }
        userService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
