package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.entity.Role;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.service.RoleService;
import ua.nure.sliva.SummaryTask4.service.UserService;
import ua.nure.sliva.SummaryTask4.util.UserValidator;
import ua.nure.sliva.SummaryTask4.web.listener.ContextListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    private UserService userService;
    private RoleService roleService;
    private UserValidator userValidator;
    @Override
    public void init() throws ServletException {
        roleService = (RoleService)getServletContext().getAttribute("roleService") ;
        userService = (UserService) getServletContext().getAttribute("userService");
        userValidator = new UserValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")!=null){
            AppException exception = new AppException("You already loginned");
            request.setAttribute("exception",exception);
            throw exception;
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String err = userValidator.validate(login,password);
        if(err != null){
            request.getSession().setAttribute("err",err);
            response.sendRedirect("login.jsp");
            return;
        }
        User user = userService.tryToLogin(login,password);
        if(user == null){
            request.getSession().setAttribute("err","Incorrect login or password");
            request.getSession().setAttribute("login",login);
            response.sendRedirect("login.jsp");
            return;
        }
        request.getSession().setAttribute("user",user);
        Role role = roleService.getRoleById(user.getRole());
        request.getSession().setAttribute("role",role);
        response.sendRedirect("index");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
