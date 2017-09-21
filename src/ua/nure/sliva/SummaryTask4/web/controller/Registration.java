package ua.nure.sliva.SummaryTask4.web.controller;

import org.apache.log4j.Logger;
import ua.nure.sliva.SummaryTask4.constants.Parameters;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    private UserValidator userValidator;
    private UserService userService;
    private RoleService roleService;
    private static final Logger LOG = Logger.getLogger(Registration.class);

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
        userValidator = new UserValidator();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Parameters.USER_LOGIN);
        String name = request.getParameter(Parameters.NAME);
        String email = request.getParameter(Parameters.USER_EMAIL);
        String password = request.getParameter(Parameters.USER_PASSWORD);
        String repassword = request.getParameter(Parameters.USER_REPASSWORD);
        LOG.debug(login);
        LOG.debug(name);
        LOG.debug(email);

        String err = userValidator.validate(login,name,email,password,repassword);
        if(err != null){
            LOG.error(err);
            request.getSession().setAttribute("err",err);
            response.sendRedirect("registration.jsp");
            return;
        }
        if(userService.isExist(login)){
            LOG.error("User already exist");
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
        user.setBan(false);
        if(userService.create(user) == 0){
            AppException exception = new AppException("Inner error");
            LOG.error(exception);
            request.setAttribute(Parameters.EXCEPTION,exception);
            throw exception;
        }
        Role role = roleService.getRoleById(2);
        request.getSession().setAttribute(Parameters.USER,user);
        request.getSession().setAttribute(Parameters.ROLE,role);
        response.sendRedirect("index");
    }
}
