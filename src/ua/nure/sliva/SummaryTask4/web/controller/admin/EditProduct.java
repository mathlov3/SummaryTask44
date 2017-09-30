package ua.nure.sliva.SummaryTask4.web.controller.admin;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Image;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.ProductService;
import ua.nure.sliva.SummaryTask4.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@WebServlet("/editProduct")
@MultipartConfig
public class EditProduct extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(EditProduct.class);
    private ProductService productService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        String name = request.getParameter(Parameters.NAME);
        String description = request.getParameter(Parameters.DESCRIPTION);
        String category = request.getParameter(Parameters.CATEGORY);
        String allDesc = request.getParameter(Parameters.ALLDESC);
        int count = Integer.parseInt(request.getParameter(Parameters.COUNT));
        double price = Double.parseDouble(request.getParameter(Parameters.PRICE));
        Product product = new Product();
        product.setId(id);
        product.setPrice(price);
        product.setCategoryId(Integer.parseInt(category));
        product.setCount(count);
        product.setDescription(description);
        product.setName(name);
        product.setAllDesc(allDesc);
        Part filePart = request.getPart("file");
        if(filePart.getSize() !=0) {
            InputStream filecontent = null;
            try {
                filecontent = filePart.getInputStream();
                byte[] bytes = new byte[filecontent.available()];
                filecontent.read(bytes);
                product.setImg(bytes);
            } catch (IOException e) {
                throw new UnsupportedOperationException(e);

            } finally {
                if (Objects.nonNull(filecontent)) {
                    filecontent.close();
                }
            }
        }
        productService.updateProduct(product);
        if(count>0){
            userService.notifyUsers(product.getId());
        }
        response.sendRedirect("editProduct?id="+id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        Product product = productService.getProductById(id);
        List<Image> images = productService.getProductImages(id);
        request.setAttribute(Parameters.PRODUCT,product);
        request.setAttribute(Parameters.IMAGES,images);
        request.getRequestDispatcher("WEB-INF/editProduct.jsp").forward(request,response);
    }
}
