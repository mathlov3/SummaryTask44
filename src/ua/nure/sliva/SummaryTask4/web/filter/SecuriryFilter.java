package ua.nure.sliva.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = {"/addProduct", "/admin", "/editProduct", "/getOrders", "/getUsers", "/productList", "/updateImage", "/updateOrder", "/updateUserStatus"})
public class SecuriryFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SecuriryFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Role role = (Role) ((HttpServletRequest) req).getSession().getAttribute("role");
        if (Objects.isNull(role) || role.getId() != 1) {
            LOG.debug("Invalid access");
            ((HttpServletResponse) resp).sendRedirect("index");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
