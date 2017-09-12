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

    public OrderServiceImpl(OrderDao orderDao,TransactionPool transactionPool){
        this.orderDao = orderDao;
        this.transactionPool = transactionPool;
    }

    @Override
    public int tryToCreate(Order order) {
        transactionPool.execute(new Transaction<Order>() {
            @Override
            public Order execute() throws SQLException {

                int id = orderDao.create(order);
                order.setId(id);
                orderDao.addProductsToOrder(order.getProducts(),order.getId());
                return order;
            }
        });
        return order.getId();
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        List<Order> orders = new ArrayList<>();
        transactionPool.execute(new Transaction<Order>() {
            @Override
            public Order execute() throws SQLException {
                orders.addAll(orderDao.getOrdersByUserId(id));
                return null;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return orders;
    }

}
