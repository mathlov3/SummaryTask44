package ua.nure.sliva.SummaryTask4.web.controller;

import ua.nure.sliva.SummaryTask4.entity.Role;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.exception.AppException;
import ua.nure.sliva.SummaryTask4.service.RoleService;
import ua.nure.sliva.SummaryTask4.service.UserService;
import ua.nure.sliva.SummaryTask4.util.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    private UserValidator userValidator;
    private UserService userService;
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
        userValidator = new UserValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        String err = userValidator.validate(login,name,email,password,repassword);
        if(err != null){
            request.getSession().setAttribute("err",err);
            response.sendRedirect("registration.jsp");
            return;
        }
        if(userService.isExist(login)){
            request.getSession().setAttribute("err","User already exist");
            response.sendRedirect("registration.jsp");
            return;
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRoleId(2);
        user.setLogin(login);
        if(userService.create(user) == 0){
            AppException exception = new AppException("Inner error");
            request.setAttribute("exception",exception);
            throw exception;
        }
        Role role = roleService.getRoleById(1);
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("role",role);
        response.sendRedirect("index");
    }
}
