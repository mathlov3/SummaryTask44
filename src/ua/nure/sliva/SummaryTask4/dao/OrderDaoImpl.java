package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.constants.R;
import ua.nure.sliva.SummaryTask4.dao.mapper.OrderMapper;
import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private OrderMapper orderMapper = new OrderMapper();
    @Override
    public Order getById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        Order order = null;
        try (PreparedStatement ps = connection.prepareStatement(R.GET_ORDER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = orderMapper.map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
        return order;
    }

    @Override
    public int create(Order entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(R.CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            System.out.println("Creating order");
            ps.setDouble(++k, entity.getPrice());
            ps.setInt(++k, entity.getUsersId());
            ps.setInt(++k, 1);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addProductsToOrder(Map<Product, Integer> products, int orderId) {
        Connection connection = ThreadLocaleHandler.getConnection();
        boolean result = true;
        try (PreparedStatement ps = connection.prepareStatement(R.ADD_PRODUCT_TO_ORDER)) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                int k = 0;
                ps.setInt(++k, orderId);
                ps.setInt(++k, entry.getKey().getId());
                ps.setInt(++k, entry.getValue());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return result;
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        List<Order> orders = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(R.GET_ORDERS_BY_USER_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = orderMapper.map(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new UnsupportedOperationException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByStatusId(int id) {
        List<Order> orders = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        String sql = "";
        switch (id) {
            case 0: {
                sql = R.GET_ALL_ORDERS;
                break;
            }
            case 1: {
                sql = R.GET_NEW_ORDERS;
                break;
            }
            case 2: {
                sql = R.GET_ACCEPTED_ORDERS_ORDERS;
                break;
            }
            case 3: {
                sql = R.GET_DISABLED_ORDERS_ORDERS;
                break;
            }
            default: {
                sql = R.GET_ALL_ORDERS;
            }
        }
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int k = 0;
                Order order = orderMapper.map(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return orders;
    }
}
