package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.dao.ProductDAO;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AddToCart.class);

    private ProductService productService;
    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(Parameters.ID));
        Product product = productService.getProductById(id);
        LOG.debug(product);
        int count = Integer.parseInt(req.getParameter(Parameters.COUNT));
        if(count<=0){
            AppException exception = new AppException("Count products cannot be less 1");
            req.setAttribute("exception",exception);
            throw exception;
        }
        if(product != null){
            ((Cart<Product>)req.getSession().getAttribute(Parameters.CART)).put(product,count);
        }
        req.getSession().setAttribute("okb","ok");
        resp.sendRedirect("/product?id="+id);
    }


}
