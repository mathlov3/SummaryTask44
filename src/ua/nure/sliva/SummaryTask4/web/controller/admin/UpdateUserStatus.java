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
import java.util.Objects;

@WebServlet("/updateUserStatus")
public class UpdateUserStatus extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter(Parameters.USER_ID));
        if(userId != 1) {
            if (Objects.nonNull(request.getParameter("banUser"))) {
                int banUser = Integer.parseInt(request.getParameter("banUser"));
                userService.changeBanUser(userId, banUser);
            } else {
                int newRole = Integer.parseInt(request.getParameter("newRole"));
                userService.changeRole(userId, newRole);
            }
        }
        response.sendRedirect("getUsers");
    }
}
