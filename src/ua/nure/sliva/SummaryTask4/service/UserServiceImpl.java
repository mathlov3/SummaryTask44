package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.dao.UserDAO;
import ua.nure.sliva.SummaryTask4.entity.Commentary;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private TransactionPool transactionPool;

    public UserServiceImpl(UserDAO userDAO, TransactionPool transactionPool) {
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

        if (Objects.nonNull(user)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int create(User user) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return userDAO.create(user);
            }
        });
    }

    @Override
    public User tryToLogin(final String login, final String password) {
        return transactionPool.execute(new Transaction<User>() {
            @Override
            public User execute() throws SQLException {
                User u = userDAO.getByLoginAndPassword(login, password);
                return u;
            }
        });
    }

    @Override
    public Map<Commentary, User> getCommentaryWithUsers(List<Commentary> commentaries) {
        return transactionPool.execute(new Transaction<Map<Commentary, User>>() {
            @Override
            public Map<Commentary, User> execute() throws SQLException {
                Map<Commentary,User> commentaryUserMap = new LinkedHashMap<>();
                for (Commentary commentary:commentaries){
                    if(commentary.getUsers_id() == null){
                        commentaryUserMap.put(commentary,null);
                    } else {
                        commentaryUserMap.put(commentary,userDAO.getById(commentary.getUsers_id()));
                    }
                }
                return commentaryUserMap;
            }
        });
    }

    @Override
    public List<User> getAllUsers() {
        return transactionPool.execute(new Transaction<List<User>>() {
            @Override
            public List<User> execute() throws SQLException {
                return userDAO.getAll();
            }
        });
    }

    @Override
    public void changeBanUser(int userId, int userBan) {
        transactionPool.execute(new Transaction<Object>() {
            @Override
            public Object execute() throws SQLException {
                User user = userDAO.getById(userId);
                user.setBan(userBan==1?true:false);
                userDAO.update(user);
                return null;
            }
        });
    }

    @Override
    public void changeRole(int userId, int newRole) {
        transactionPool.execute(new Transaction<Object>() {
            @Override
            public Object execute() throws SQLException {
                User user = userDAO.getById(userId);
                user.setRole(newRole);
                userDAO.update(user);
                return null;
            }
        });
    }
}
