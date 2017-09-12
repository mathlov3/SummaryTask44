package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.constants.R;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order getById(int id) {
        return null;
    }

    @Override
    public int create(Order entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(R.CREATE_ORDER,PreparedStatement.RETURN_GENERATED_KEYS)){
            int k = 0;
            System.out.println("Creating order");
            ps.setDouble(++k,entity.getPrice());
            ps.setInt(++k,entity.getUsersId());
            ps.setInt(++k,1);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
        return id;
    }

    @Override
    public int update(Order entity) {
        return 0;
    }

    @Override
    public int delete(Order entity) {
        return 0;
    }

    @Override
    public boolean addProductsToOrder(Map<Product, Integer> products,int orderId) {
        Connection connection = ThreadLocaleHandler.getConnection();
        boolean result = true;
        try(PreparedStatement ps = connection.prepareStatement(R.ADD_PRODUCT_TO_ORDER)) {
            for (Map.Entry<Product,Integer> entry:products.entrySet()){
                int k = 0;
                ps.setInt(++k,orderId);
                System.out.println(orderId);
                ps.setInt(++k,entry.getKey().getId());
                ps.setInt(++k,entry.getValue());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return result;
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        List<Order> orders = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(R.GET_ALL_ORDERS_BY_USER_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int k = 0;
                Order order = new Order();
                order.setId(rs.getInt(++k));
                order.setPrice(rs.getDouble(++k));
                order.setUsersId(rs.getInt(++k));
                order.setUsersId(rs.getInt(++k));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new UnsupportedOperationException(e);
        }
        return orders;
    }
}
