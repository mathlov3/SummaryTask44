package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.entity.Commentary;
import ua.nure.sliva.SummaryTask4.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean isExist(String login);

    int create(User user);

    User tryToLogin(String lpgin, String password);

    Map<Commentary,User> getCommentaryWithUsers(List<Commentary> commentaries);

    List<User> getAllUsers();

    void changeBanUser(int userId,int userBan);

    void changeRole(int userId,int newRole);
}
