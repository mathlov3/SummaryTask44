package ua.nure.sliva.SummaryTask4.web.controller;

import ua.nure.sliva.SummaryTask4.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productList")
public class ProductList extends HttpServlet {
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
        int category = Integer.parseInt(request.getParameter("category"));
        int page = Integer.parseInt(request.getParameter("page"));
        int countProducts = productService.getCountProductsInCategory(category);
        request.setAttribute("products",productService.getPaginableProducts(page*PAGE_CONTENT-PAGE_CONTENT,PAGE_CONTENT,category));
        request.setAttribute("page",page);
        request.getSession().setAttribute("countProducts",countProducts);
        request.setAttribute("category",category);
        request.getRequestDispatcher("products.jsp").forward(request,response);
    }
}
