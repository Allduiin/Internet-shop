package internetshop.controllers;

import internetshop.lib.Injector;
import internetshop.model.User;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/registration")
public class RegistrationController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String repeatPassword = req.getParameter("pwd-repeat");
        if (password.equals(repeatPassword)) {
            if (userService.findByLogin(login).isPresent()) {
                req.setAttribute("message", "This login is already existed");
                req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
            } else {
                userService.create(new User(login, password));
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            req.setAttribute("message", "Your passwords are ot equals");
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
