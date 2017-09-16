package ua.nure.sliva.SummaryTask4.web.controller.admin;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;


@WebServlet("/addProduct")
@MultipartConfig
public class AddProduct extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        int count = Integer.parseInt(request.getParameter("count"));
        double price = Double.parseDouble(request.getParameter("price"));
        Product product = new Product();
        product.setPrice(price);
        product.setCategoryId(Integer.parseInt(category));
        product.setCount(count);
        product.setDescription(description);
        product.setName(name);
        Part filePart = request.getPart("file");
        InputStream filecontent = null;
        try {
            filecontent = filePart.getInputStream();
            byte[] bytes = new byte[filecontent.available()];
            filecontent.read(bytes);
            product.setImg(bytes);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);

        } finally {
            if (filecontent != null) {
                filecontent.close();
            }

        }
        productService.addProduct(product);
        response.sendRedirect("addProduct.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void imageUtil(HttpServletRequest request) throws IOException, ServletException {
        try {



        } catch (Exception e){
            e.printStackTrace();

        }
    }
}
