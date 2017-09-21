package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.entity.User;

import java.util.List;
import java.util.Set;

public interface UserDAO extends GenericDAO<User> {
    User getByLogin(String login);

    List<User> getAll();

    User getByLoginAndPassword(String login, String password);

    Set<User> getWaiting(int productId);

    int addWaiting(int userId, int productId);

    int deleteWaiting(int productId);
}
