package internetshop.controllers.order;

import internetshop.lib.Injector;
import internetshop.service.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/orders/orderInfo")
public class GetOrderInfoController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final OrderService orderService =
            (OrderService) INJECTOR.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("order",
                orderService.getById(Long.valueOf(req.getParameter("id"))));
        req.getRequestDispatcher("/WEB-INF/views/orders/orderInfo.jsp").forward(req, resp);
    }
}
