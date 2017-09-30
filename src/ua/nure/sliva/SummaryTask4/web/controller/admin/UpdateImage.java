package ua.nure.sliva.SummaryTask4.web.controller.admin;

import ua.nure.sliva.SummaryTask4.constants.Parameters;
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
import java.util.Objects;

@WebServlet("/updateImage")
@MultipartConfig
public class UpdateImage extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        if (Objects.nonNull(request.getPart("file2"))) {
            Part filePart = request.getPart("file2");
            InputStream filecontent = null;
            try {
                filecontent = filePart.getInputStream();
                byte[] bytes = new byte[filecontent.available()];
                filecontent.read(bytes);
                productService.addImageForProduct(bytes, id);
            } catch (IOException e) {
                throw new UnsupportedOperationException(e);

            } finally {
                if (Objects.nonNull(filecontent)) {
                    filecontent.close();
                }
            }
        } else {
            int imageId = Integer.parseInt(request.getParameter("imageId"));
            productService.deleteImage(imageId);
        }
        response.sendRedirect("editProduct?id=" + id);
    }
}
