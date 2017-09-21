package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
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

@WebServlet("/orderProducts")
public class GetMyOrderProducts extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(GetMyOrderProducts.class);

    private ProductService productService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        productService = (ProductService) getServletContext().getAttribute("productService");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute(Parameters.USER)==null){
            AppException exception = new AppException("You need login if you want show orders and ordered products");
            LOG.error(exception);
            request.setAttribute(Parameters.EXCEPTION,exception);
            throw exception;
        }
        int orderId = Integer.parseInt(request.getParameter(Parameters.ORDER_ID));
        boolean access = orderService.isUserHaveOrder(((User)request.getSession().getAttribute(Parameters.USER)).getId(),orderId);
        if(!access){
            AppException exception = new AppException("You don't have access to this page");
            request.setAttribute(Parameters.EXCEPTION,exception);
            throw exception;
        }
        Order order = orderService.getOrderById(orderId);
        List<Product> products = productService.getProductsByOrderId(orderId);
        LOG.debug(products);
        request.setAttribute(Parameters.PRODUCTS,products);
        request.setAttribute(Parameters.ORDER,order);
        request.getRequestDispatcher("myOrderProducts.jsp").forward(request,response);
    }
}
