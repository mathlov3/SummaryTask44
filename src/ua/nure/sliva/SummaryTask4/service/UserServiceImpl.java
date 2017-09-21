package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.dao.ProductDAO;
import ua.nure.sliva.SummaryTask4.dao.UserDAO;
import ua.nure.sliva.SummaryTask4.entity.Commentary;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.*;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private ProductDAO productDAO;
    private TransactionPool transactionPool;

    public UserServiceImpl(UserDAO userDAO, TransactionPool transactionPool,ProductDAO productDAO) {
        this.userDAO = userDAO;
        this.transactionPool = transactionPool;
        this.productDAO = productDAO;
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
                int id = userDAO.create(user);
                if(id!=0){
                    final String username = "eugene.shop.m@gmail.com";
                    final String password = "1q2w3eQQ";

                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                }
                            });

                    try {

                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(username));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
                        message.setSubject("Registration <mine>SHOP");
                        message.setText("Hi "+user.getName() + " ,"+System.lineSeparator()+
                        "Thank you gor registration your profile. " + System.lineSeparator() +
                        "You can buy different products on our <mine>SHOP."+System.lineSeparator()+
                        "<a href=\"localhost:8080\">Click here</a> if you want go to shop"+System.lineSeparator()+
                        "Created for test final project.");

                        Transport.send(message);

                    } catch (MessagingException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
                return id;
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

    @Override
    public User getUserByLogin(String login) {
        return transactionPool.execute(new Transaction<User>() {
            @Override
            public User execute() throws SQLException {
                return userDAO.getByLogin(login);
            }
        });
    }

    @Override
    public void notifyUsers(int productId) {
        Set<User> users = transactionPool.execute(new Transaction<Set<User>>() {
            @Override
            public Set<User> execute() throws SQLException {
                return userDAO.getWaiting(productId);
            }
        });
        Product product = transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                return productDAO.getById(productId);
            }
        });
        transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return userDAO.deleteWaiting(productId);
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                final String username = "eugene.shop.m@gmail.com";
                final String password = "1q2w3eQQ";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });
                for (User user:users){
                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(username));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
                        message.setSubject("Products on starage <mine>SHOP");
                        message.setText(new StringBuilder().append("Hi ").append(user.getName()).append(" ,").append(System.lineSeparator())
                                .append("You asked notify when product ").append(product.getName()).append(" will be on storage.")
                                .append(System.lineSeparator()).append("Product - ").append(product.getName()).append(" on storage").append(System.lineSeparator())
                                .append("Created for test final project").toString());

                        Transport.send(message);

                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public int addWaiting(int userId, int productId) {
         return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return userDAO.addWaiting(userId,productId);

            }
        });
    }
}
