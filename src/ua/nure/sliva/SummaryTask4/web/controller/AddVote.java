package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

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
    private static final Logger LOG = Logger.getLogger(AddVote.class);

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute(Parameters.USER) == null){
            AppException exception = new AppException("You must be logined if you want vote for product");
            request.setAttribute(Parameters.EXCEPTION,exception);
            throw exception;
        }
        int vote = Integer.parseInt(request.getParameter(Parameters.VOTE));
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        LOG.debug(vote);
        LOG.debug(id);

        try {
            productService.voteForProduct(id,((User)request.getSession().getAttribute(Parameters.USER)).getId(),vote);
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
