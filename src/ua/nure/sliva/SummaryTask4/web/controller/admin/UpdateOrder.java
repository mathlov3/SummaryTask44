package ua.nure.sliva.SummaryTask4.web.controller.admin;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.service.OrderService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/updateOrder")
public class UpdateOrder extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(UpdateOrder.class);

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug(request.getParameter(Parameters.STATUS));
        LOG.debug(request.getParameter(Parameters.ID));
        int status = Integer.parseInt(request.getParameter(Parameters.STATUS));
        int id = Integer.parseInt(request.getParameter(Parameters.ID));
        String content = request.getParameter(Parameters.CONTENT);
        boolean st = orderService.updateOrder(id,status,content);
        request.getSession().setAttribute("status",st?"Operation succsessfull":"Operation denied");
        response.sendRedirect("getOrders?status=1");
    }
}
