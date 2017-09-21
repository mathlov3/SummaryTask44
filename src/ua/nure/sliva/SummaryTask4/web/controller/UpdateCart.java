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

@WebServlet("/updateCart")
public class UpdateCart extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(UpdateCart.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(request.getParameter(Parameters.ID));
        LOG.debug(request.getParameter(Parameters.PRODUCT_COUNT));
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        int count = Integer.parseInt(request.getParameter(Parameters.PRODUCT_COUNT));
        Cart<Product> cart = (Cart<Product>) request.getSession().getAttribute(Parameters.CART);
        Product product = new Product();
        product.setId(id);
        cart.add(product,count);
        response.sendRedirect("cart.jsp");
    }
}
