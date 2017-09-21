package ua.nure.sliva.SummaryTask4.web.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/getReport")
public class GenerateReport extends HttpServlet {
    private OrderService orderService;
    private ProductService productService;
    private static final Logger LOG = Logger.getLogger(GenerateReport.class);

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrderById(id);
        LOG.debug(order);
        List<Product> products = productService.getProductsByOrderId(id);
        LOG.debug(products);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("text.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        Anchor anchorTarget = new Anchor("Order #"+id);
        anchorTarget.setName("BackToTop");
        Paragraph paragraph1 = new Paragraph();

        paragraph1.setSpacingBefore(50);

        paragraph1.add(anchorTarget);
        try {
            document.add(paragraph1);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }

        try {
            document.add(new Paragraph("Products: ",
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(255, 0, 0, 0))));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }

        PdfPTable t = new PdfPTable(4);
        t.setSpacingBefore(25);
        t.setSpacingAfter(25);
        PdfPCell c1 = new PdfPCell(new Phrase("Name"));
        PdfPCell c2 = new PdfPCell(new Phrase("Count"));
        PdfPCell c3 = new PdfPCell(new Phrase("Price"));
        PdfPCell c4 = new PdfPCell(new Phrase("Total"));
        t.addCell(c1);
        t.addCell(c2);
        t.addCell(c3);
        t.addCell(c4);
        for (Product product:products){
            PdfPCell s1 = new PdfPCell(new Phrase(product.getName()));
            PdfPCell s2 = new PdfPCell(new Phrase(""+product.getCount()));
            PdfPCell s3 = new PdfPCell(new Phrase(""+product.getPrice()));
            PdfPCell s4 = new PdfPCell(new Phrase(""+(product.getCount()*product.getPrice())));
            t.addCell(s1);
            t.addCell(s2);
            t.addCell(s3);
            t.addCell(s4);
        }
        try {
            document.add(t);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            document.add(new Paragraph("Total price - "+order.getPrice(),
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(255, 0, 0, 0))));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        document.close();
        Path path = Paths.get("text.pdf");
        response.setContentType("application/pdf");
        response.setHeader("report","report");
        byte[] data = Files.readAllBytes(path);

        response.getOutputStream().write(data);
    }
}
