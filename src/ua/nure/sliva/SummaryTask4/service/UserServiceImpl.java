package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.dao.UserDAO;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private TransactionPool transactionPool;

    public UserServiceImpl(UserDAO userDAO,TransactionPool transactionPool){
        this.userDAO = userDAO;
        this.transactionPool = transactionPool;
    }

    @Override
    public boolean isExist(final String login) {
        User user = transactionPool.execute(new Transaction<User>() {
            @Override
            public User execute() throws SQLException {
                return userDAO.getByLogin(login);
            }
        });

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public User tryToLogin(final String login, final String password) {
        User user =
        transactionPool.execute(new Transaction<User>() {
            @Override
            public User execute() throws SQLException {
                User u = userDAO.getByLoginAndPassword(login,password);
                return u;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return user;
    }
}
