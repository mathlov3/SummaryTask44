package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.dao.OrderDao;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import java.sql.SQLException;

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

}
