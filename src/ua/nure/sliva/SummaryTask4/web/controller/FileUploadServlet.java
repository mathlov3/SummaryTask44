package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Base64;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Part filePart = request.getPart("file");
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();
        try {
            filecontent = filePart.getInputStream();
            byte []bytes = new byte[filecontent.available()];
            filecontent.read(bytes);
            request.setAttribute("img",Base64.getEncoder().encodeToString(bytes));
        } catch (IOException e) {
            writer.println("<br/> ERROR: " + e.getMessage());
            throw new UnsupportedOperationException(e);

        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}