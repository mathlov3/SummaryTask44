package ua.nure.sliva.SummaryTask4.web.controller.admin;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productList")
public class ProductList extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    private static final int PAGE_CONTENT = 10;
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category = Integer.parseInt(request.getParameter(Parameters.CATEGORY));
        int page = Integer.parseInt(request.getParameter(Parameters.PAGE));
        int countProducts = productService.getCountProductsInCategory(category);
        request.setAttribute(Parameters.PRODUCTS,productService.getPaginableProducts(page*PAGE_CONTENT-PAGE_CONTENT,PAGE_CONTENT,category));
        request.setAttribute(Parameters.PAGE,page);
        request.getSession().setAttribute(Parameters.COUNT_PRODUCTS,countProducts);
        request.setAttribute(Parameters.CATEGORY,category);
        request.getRequestDispatcher("productList.jsp").forward(request,response);
    }
}
