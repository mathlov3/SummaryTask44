package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.entity.Commentary;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.service.CommentaryService;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCommentary")
public class AddCommentary extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);
    private CommentaryService commentaryService;

    @Override
    public void init() throws ServletException {
        super.init();
        commentaryService = (CommentaryService) getServletContext().getAttribute("commentaryService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        Commentary commentary = new Commentary();
        commentary.setProducts_id(id);
        commentary.setContent(content);
        commentary.setUsers_id(user==null?null:user.getId());
        commentaryService.addCommentary(commentary);
        request.getSession().setAttribute("addcomm","ok");
        response.sendRedirect("/product?id="+id);
    }


}