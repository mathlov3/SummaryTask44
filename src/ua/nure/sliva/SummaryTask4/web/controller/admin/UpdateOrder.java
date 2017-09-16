package ua.nure.sliva.SummaryTask4.web.controller.admin;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateOrder")
public class UpdateOrder extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = Integer.parseInt(request.getParameter("status"));
        System.out.println(status);
        int id = Integer.parseInt(request.getParameter("id"));
        boolean st = orderService.updateOrder(id,status);
        request.getSession().setAttribute("status",st?"Operation succsessfull":"Operation denied");
        response.sendRedirect("getOrders?status=1");
    }
}
