package ua.nure.sliva.SummaryTask4.web.controller;

import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addVote")
public class AddVote extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user") == null){
            AppException exception = new AppException("You must be logined if you want vote for product");
            request.setAttribute("exception",exception);
            throw exception;
        }
        int vote = Integer.parseInt(request.getParameter("vote"));
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            productService.voteForProduct(id,((User)request.getSession().getAttribute("user")).getId(),vote);
            request.getSession().setAttribute("voteStatus","Your vote was accepte");
        } catch (DBException e){
            request.getSession().setAttribute("voteStatus","Your alredy vote for this product");
        }
        response.sendRedirect("/product?id="+id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
