package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.entity.User;

public interface UserService {
    boolean isExist(String login);

    int create(User user);

    User tryToLogin(String lpgin, String password);
}
