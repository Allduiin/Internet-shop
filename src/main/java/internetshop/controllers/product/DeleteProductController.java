package internetshop.controllers.product;

import internetshop.lib.Injector;
import internetshop.service.ProductService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/products/delete")
public class DeleteProductController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        productService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/products/adminPage");
    }
}
