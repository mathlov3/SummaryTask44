package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.entity.Commentary;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.service.CommentaryService;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.service.UserService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/product")
public class GetProduct extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);
    private CommentaryService commentaryService;
    private ProductService productService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
        commentaryService = (CommentaryService) getServletContext().getAttribute("commentaryService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        List<Commentary> commentaries=commentaryService.getCommentariesByProductId(id);
        Map<Commentary,User> commentaryUserMap = userService.getCommentaryWithUsers(commentaries);
        int vote = productService.getProductVote(id);
        System.out.println(vote);
        request.setAttribute("product",product);
        request.setAttribute("vote",vote);
        request.setAttribute("commentaries",commentaryUserMap);
        request.getRequestDispatcher("/product.jsp").forward(request,response);
    }
}
