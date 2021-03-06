package ua.nure.sliva.SummaryTask4.web.controller.admin;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/getOrders")
public class GetOrders extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(GetOrders.class);

    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(request.getParameter(Parameters.STATUS));
        int status = Integer.parseInt(Objects.isNull(request.getParameter(Parameters.STATUS)) || request.getParameter(Parameters.STATUS).isEmpty()?"0":request.getParameter(Parameters.STATUS));
        List<Order> orders = orderService.getOrdersByStatusId(status);
        Map<Order,List<Product>> fullOrders = new LinkedHashMap<>();
        LOG.debug(fullOrders);
        for(Order order:orders){
            fullOrders.put(order,productService.getProductsByOrderId(order.getId()));
        }
        request.setAttribute("fullOrders",fullOrders);
        request.getRequestDispatcher("WEB-INF/getOrders.jsp").forward(request,response);
    }
}
