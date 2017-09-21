package ua.nure.sliva.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        String encodingParam = config.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

}
