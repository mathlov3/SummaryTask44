package ua.nure.sliva.SummaryTask4.web.listener;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.util.ProductParams;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Cart<Product> cart = new Cart<>();
        httpSessionEvent.getSession().setAttribute("cart",cart);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
