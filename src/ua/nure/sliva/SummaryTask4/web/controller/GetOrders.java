package ua.nure.sliva.SummaryTask4.web.controller;

import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getOrders")
public class GetOrders extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")==null){
            AppException exception = new AppException("You need login if you want show your orders");
            request.setAttribute("exception",exception);
            throw exception;
        }
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("myOrders.jsp").forward(request,response);
    }
}
