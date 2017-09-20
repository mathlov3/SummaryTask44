package ua.nure.sliva.SummaryTask4.web.controller.admin;

import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.Image;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.ProductService;

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

@WebServlet("/editProduct")
@MultipartConfig
public class EditProduct extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        String name = request.getParameter(Parameters.NAME);
        String description = request.getParameter(Parameters.DESCRIPTION);
        String category = request.getParameter(Parameters.CATEGORY);
        int count = Integer.parseInt(request.getParameter(Parameters.COUNT));
        double price = Double.parseDouble(request.getParameter(Parameters.PRICE));
        Product product = new Product();
        product.setId(id);
        product.setPrice(price);
        product.setCategoryId(Integer.parseInt(category));
        product.setCount(count);
        product.setDescription(description);
        product.setName(name);
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
                if (filecontent != null) {
                    filecontent.close();
                }
            }
        }

        productService.updateProduct(product);
        response.sendRedirect("editProduct?id="+id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        Product product = productService.getProductById(id);
        List<Image> images = productService.getProductImages(id);
        request.setAttribute(Parameters.PRODUCT,product);
        request.setAttribute(Parameters.IMAGES,images);
        request.getRequestDispatcher("editProduct.jsp").forward(request,response);
    }
}
