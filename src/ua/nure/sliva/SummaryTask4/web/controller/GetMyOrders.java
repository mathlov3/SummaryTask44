package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getMyOrders")
public class GetMyOrders extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(GetMyOrders.class);

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute(Parameters.USER)==null){
            AppException exception = new AppException("You need login if you want show your orders");
            LOG.error(exception);
            request.setAttribute(Parameters.EXCEPTION,exception);
            throw exception;
        }
        User user = (User) request.getSession().getAttribute(Parameters.USER);
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        LOG.debug(orders);
        request.setAttribute(Parameters.ORDERS,orders);
        request.getRequestDispatcher("myOrders.jsp").forward(request,response);
    }
}
