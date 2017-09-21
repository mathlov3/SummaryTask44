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

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Cart<Product> cart = new Cart<>();
        httpSessionEvent.getSession().setAttribute("cart",cart);
        httpSessionEvent.getSession().setAttribute("locale","en");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
