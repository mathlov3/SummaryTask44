package ua.nure.sliva.SummaryTask4.web.controller;

import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderProducts")
public class GetOrderProducts extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        productService = (ProductService) getServletContext().getAttribute("productService");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")==null){
            AppException exception = new AppException("You need login if you want show orders and ordered products");
            request.setAttribute("exception",exception);
            throw exception;
        }
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        boolean access = orderService.isUserHaveOrder(((User)request.getSession().getAttribute("user")).getId(),orderId);
        if(!access){
            AppException exception = new AppException("You don't have access to this page");
            request.setAttribute("exception",exception);
            throw exception;
        }
        List<Product> products = productService.getProductsByOrderId(orderId);
        request.setAttribute("products",products);
        request.setAttribute("orderId",orderId);
        request.getRequestDispatcher("myOrderProducts.jsp").forward(request,response);
    }
}
