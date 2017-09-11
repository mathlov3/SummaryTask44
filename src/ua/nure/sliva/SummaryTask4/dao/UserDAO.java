package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.entity.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {
    User getByLogin(String login);

    List<User> getAll();

    User getByLoginAndPassword(String login, String password);
}
