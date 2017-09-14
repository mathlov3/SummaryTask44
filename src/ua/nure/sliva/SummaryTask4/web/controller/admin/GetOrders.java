package ua.nure.sliva.SummaryTask4.web.controller.admin;

import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/getOrders")
public class GetOrders extends HttpServlet {
    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = Integer.parseInt(request.getParameter("status") == null || request.getParameter("status").isEmpty()?"0":request.getParameter("status"));
        List<Order> orders = orderService.getOrdersByStatusId(status);
        Map<Order,List<Product>> fullOrders = new HashMap<>();
        for(Order order:orders){
            fullOrders.put(order,productService.getProductsByOrderId(order.getId()));
        }
        request.setAttribute("fullOrders",fullOrders);
        request.getRequestDispatcher("getOrders.jsp").forward(request,response);
    }
}
