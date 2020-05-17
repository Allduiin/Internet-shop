package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/orders/delete")
public class DeleteOrderController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        orderService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
