package ua.nure.sliva.SummaryTask4.web.controller;

import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createOrder")
public class CreateOrder extends HttpServlet {
    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        Cart<Product> cart = (Cart<Product>) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        order.setPrice(cart.getPrice());
        order.setUsersId(user.getId());
        order.setProducts(cart);
        try {
            orderService.tryToCreate(order);
            request.getRequestDispatcher("clearCart").include(request,response);
            response.sendRedirect("index.jsp");
        } catch (DBException e){
            List<Product> errors = productService.getProductsThatLagestInOrder(cart);
            request.setAttribute("errors",errors);
            System.out.println(errors);
            request.getRequestDispatcher("cart.jsp").forward(request,response);
            return;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
