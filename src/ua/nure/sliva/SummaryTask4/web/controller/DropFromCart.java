package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import org.gjt.mm.mysql.Driver;
import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@WebServlet("/dropFromCart")
public class DropFromCart extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(DropFromCart.class);

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart<Product> cart = (Cart<Product>) request.getSession().getAttribute(Parameters.CART);
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        LOG.debug(id);
        Product product = new Product();
        product.setId(id);
        cart.remove(product);
        response.sendRedirect("cart.jsp");
    }
}
