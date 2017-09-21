package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeLocale")
public class ChangeLocale extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ChangeLocale.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newLocale = request.getParameter("newLocale");
        LOG.debug("new locale - "+newLocale);
        request.getSession().setAttribute("locale",newLocale);
        response.sendRedirect("index");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
