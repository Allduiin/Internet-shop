package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.model.Order;
import internetshop.service.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    OrderService orderService = (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.getAll();

        req.setAttribute("orders",orders);
        req.getRequestDispatcher("").forward(req, resp);
    }
}
