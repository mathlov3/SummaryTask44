package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clearCart")
public class ClearCart extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ClearCart.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart<Product> cart = (Cart<Product>) request.getSession().getAttribute(Parameters.CART);
        cart.clear();
        LOG.debug("Clearing cart");
        response.sendRedirect("cart.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
