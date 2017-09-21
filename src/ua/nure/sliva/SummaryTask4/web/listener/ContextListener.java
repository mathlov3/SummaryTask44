package ua.nure.sliva.SummaryTask4.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.sliva.SummaryTask4.dao.*;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.service.*;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext sc = servletContextEvent.getServletContext();
        initLog4J(sc);
        DataSource ds = null;

        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/root");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }

        TransactionPool transactionPool = new TransactionPool(ds);

        UserDAO userDAO = new UserDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        RoleDao roleDao = new RoleDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        CommentaryDAO commentaryDAO = new CommentaryDAOImpl();

        UserService userService = new UserServiceImpl(userDAO,transactionPool,productDAO);
        ProductService productService = new ProductServiceImpl(productDAO,transactionPool);
        RoleService roleService = new RoleServiceImpl(roleDao,transactionPool);
        OrderService orderService = new OrderServiceImpl(orderDao,transactionPool);
        CommentaryService commentaryService = new CommentaryServiceImpl(commentaryDAO,transactionPool);


        sc.setAttribute("userService",userService);
        sc.setAttribute("orderService",orderService);
        sc.setAttribute("roleService",roleService);
        sc.setAttribute("productService",productService);
        sc.setAttribute("commentaryService",commentaryService);

        List<Category> categories = new ArrayList<>();
        transactionPool.execute(new Transaction<Category>() {
            @Override
            public Category execute() throws SQLException {
                categories.addAll(productDAO.getAllCategory());
                return null;
            }
        });

        sc.setAttribute("categories",categories);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void initLog4J(ServletContext servletContext) {
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
