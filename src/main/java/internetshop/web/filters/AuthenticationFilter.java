package internetshop.web.filters;

import internetshop.lib.Injector;
import internetshop.service.UserService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private static final Injector INJECTOR = Injector.getInstance("internetshop");
    private static final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private static final String USER_ID = "user_id";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getServletPath();
        if (url.equals("/login") || url.equals("/registration")) {
            chain.doFilter(req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (userId == null) {
            resp.sendRedirect("/login");
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
