package ua.nure.sliva.SummaryTask4.web.controller;

import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.util.ProductParams;
import ua.nure.sliva.SummaryTask4.util.SqlBuilder;

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
    private ProductService productService;
    private SqlBuilder sqlBuilder = new SqlBuilder();

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String page = request.getParameter("page");
        String sort = request.getParameter("sort");
        List<Category> categories = new ArrayList<>();
        for(Category category: (List<Category>)this.getServletContext().getAttribute("categories")){
            if(request.getParameter(category.getName()) != null){
                categories.add(category);
            }
        }
        StringBuilder categors = new StringBuilder();
        for(Category category:categories){
            categors.append("&").append(category.getName()).append("=").append(category.getName());
        }
        ProductParams pp = (ProductParams) request.getSession().getAttribute("productParams");
        if(pp == null){
            pp = new ProductParams();
            request.getSession().setAttribute("productParams",pp);
        }
        pp.setCategories(categories);
        pp.setName(name);
        pp.setMinPrice(Double.parseDouble(minPrice == null || minPrice.isEmpty()?"0":minPrice));
        pp.setMaxPrice(Double.parseDouble(maxPrice == null|| maxPrice.isEmpty()?"0":maxPrice));
        pp.setOrderBy(sort);
        String sql = sqlBuilder.buildSqlProductWithRestrict(pp);
        List<Product> products = productService.getProductsBySql(sql,((Integer.parseInt(page ==null || page.isEmpty()?"1":page)-1))*9);
        request.setAttribute("minPrice",minPrice);
        request.setAttribute("maxPrice",maxPrice);
        request.setAttribute("name",name);
        request.setAttribute("sort",sort);
        request.setAttribute("categories",categories);
        request.setAttribute("categors",categors.toString());
        request.setAttribute("page",Integer.parseInt(page==null || page.isEmpty()?"1":page));
        request.setAttribute("products",products);
        request.setAttribute("countProducts",productService.getCountProductsBySql(sqlBuilder.buildSqlForCount(sql)));
        request.getRequestDispatcher("getProducts.jsp").forward(request,response);

    }
}
