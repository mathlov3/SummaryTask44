package ua.nure.sliva.SummaryTask4.web.controller.admin;

import ua.nure.sliva.SummaryTask4.constants.Parameters;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getUsers")
public class GetUsers extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        if(request.getParameter("login")!=null && !request.getParameter("login").isEmpty()){
            request.setAttribute("login",request.getParameter("login"));
            User user = userService.getUserByLogin(request.getParameter("login"));
            if(user!=null){
                users.add(user);
            }
        } else {
            users = userService.getAllUsers();
        }
        request.setAttribute(Parameters.USERS,users);
        request.getRequestDispatcher("WEB-INF/users.jsp").forward(request,response);
    }
}
