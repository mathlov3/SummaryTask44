package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Logout.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(request.getSession().getAttribute(Parameters.USER));
        request.getSession().setAttribute(Parameters.USER,null);
        request.getSession().setAttribute(Parameters.ROLE,null);
        response.sendRedirect("index");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
