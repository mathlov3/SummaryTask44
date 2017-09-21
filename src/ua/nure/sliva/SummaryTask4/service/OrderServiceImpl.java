package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.dao.OrderDao;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private TransactionPool transactionPool;
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao, TransactionPool transactionPool) {
        this.orderDao = orderDao;
        this.transactionPool = transactionPool;
    }

    @Override
    public int tryToCreate(Order order) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                order.setId(orderDao.create(order));
                orderDao.addProductsToOrder(order.getProducts(),order.getId());
                return order.getId();
            }
        });
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return transactionPool.execute(new Transaction<List<Order>>() {
            @Override
            public List<Order> execute() throws SQLException {
                return orderDao.getOrdersByUserId(id);
            }
        });
    }

    @Override
    public boolean isUserHaveOrder(int uId, int oId) {
        return transactionPool.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                Order order = orderDao.getById(oId);
                if (order == null) {
                } else if (order.getUsersId() == uId) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public List<Order> getOrdersByStatusId(int id) {
        return transactionPool.execute(new Transaction<List<Order>>() {
            @Override
            public List<Order> execute() throws SQLException {
                return orderDao.getOrdersByStatusId(id);
            }
        });
    }

    @Override
    public Order getOrderById(int id) {
        return transactionPool.execute(new Transaction<Order>() {
            @Override
            public Order execute() throws SQLException {
                return orderDao.getById(id);
            }
        });
    }

    @Override
    public boolean updateOrder(int id, int status,String content) {
        return transactionPool.execute(new Transaction<Boolean>() {
            @Override
            public Boolean execute() throws SQLException {
                Order order = new Order();
                order.setId(id);
                order.setOrders_status_id(status);
                order.setNote(content);
                return orderDao.update(order) == 0 ? false : true;
            }
        });
    }

}
