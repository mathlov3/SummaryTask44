package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.util.ProductParams;
import ua.nure.sliva.SummaryTask4.util.SqlBuilder;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getProducts")
public class GetProducts extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(GetProducts.class);

    private ProductService productService;
    private SqlBuilder sqlBuilder = new SqlBuilder();

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(request.getParameter(Parameters.NAME));
        LOG.debug(request.getParameter(Parameters.PRODUCT_MIN_PRICE));
        LOG.debug(request.getParameter(Parameters.PRODUCT_MAX_PRICE));
        LOG.debug(request.getParameter(Parameters.PRODUCT_PAGE));
        LOG.debug(request.getParameter(Parameters.PRODUCT_SORT));
        String name = request.getParameter(Parameters.NAME);
        String minPrice = request.getParameter(Parameters.PRODUCT_MIN_PRICE);
        String maxPrice = request.getParameter(Parameters.PRODUCT_MAX_PRICE);
        String page = request.getParameter(Parameters.PRODUCT_PAGE);
        String sort = request.getParameter(Parameters.PRODUCT_SORT);
        List<Category> categories = new ArrayList<>();
        for(Category category: (List<Category>)this.getServletContext().getAttribute(Parameters.CATEGORIES)){
            if(request.getParameter(category.getName()) != null){
                categories.add(category);
            }
        }
        StringBuilder categors = new StringBuilder();
        for(Category category:categories){
            categors.append("&").append(category.getName()).append("=").append(category.getName());
        }
        ProductParams pp = (ProductParams) request.getSession().getAttribute(Parameters.PRODUCT_PARAMS);
        if(pp == null){
            pp = new ProductParams();
            request.getSession().setAttribute(Parameters.PRODUCT_PARAMS,pp);
        }
        pp.setCategories(categories);
        pp.setName(name);
        pp.setMinPrice(Double.parseDouble(minPrice == null || minPrice.isEmpty()?"0":minPrice));
        pp.setMaxPrice(Double.parseDouble(maxPrice == null|| maxPrice.isEmpty()?"0":maxPrice));
        pp.setOrderBy(sort);
        String sql = sqlBuilder.buildSqlProductWithRestrict(pp);
        LOG.debug(sql);
        List<Product> products = productService.getProductsBySql(sql,((Integer.parseInt(page ==null || page.isEmpty()?"1":page)-1))*6);
        request.setAttribute(Parameters.PRODUCT_MIN_PRICE,minPrice);
        request.setAttribute(Parameters.PRODUCT_MAX_PRICE,maxPrice);
        request.setAttribute(Parameters.NAME,name);
        request.setAttribute(Parameters.PRODUCT_SORT,sort);
        request.setAttribute(Parameters.CATEGORIES,categories);
        request.setAttribute(Parameters.CATEGORS,categors.toString());
        request.setAttribute(Parameters.PRODUCT_PAGE,Integer.parseInt(page==null || page.isEmpty()?"1":page));
        request.setAttribute(Parameters.PRODUCTS,products);
        request.setAttribute(Parameters.COUNT_PRODUCTS,productService.getCountProductsBySql(sqlBuilder.buildSqlForCount(sql)));
        request.getRequestDispatcher("getProducts.jsp").forward(request,response);

    }
}
