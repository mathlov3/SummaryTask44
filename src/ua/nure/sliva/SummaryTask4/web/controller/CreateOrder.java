package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/createOrder")
public class CreateOrder extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CreateOrder.class);

    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Objects.isNull(request.getSession().getAttribute(Parameters.USER))){
            AppException exception = new AppException("You need login if you want make order");
            request.setAttribute(Parameters.EXCEPTION,exception);
            LOG.error(exception);
            throw exception;
        }
        Order order = new Order();
        Cart<Product> cart = (Cart<Product>) request.getSession().getAttribute(Parameters.CART);
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        LOG.debug(user);
        order.setPrice(cart.getPrice());
        order.setUsersId(user.getId());
        order.setProducts(cart);
        try {
            orderService.tryToCreate(order);
            request.getRequestDispatcher("clearCart").include(request,response);
            response.sendRedirect("cart.jsp");
        } catch (DBException e){
            LOG.error(e);
            List<Product> errors = productService.getProductsThatLagestInOrder(cart);
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
