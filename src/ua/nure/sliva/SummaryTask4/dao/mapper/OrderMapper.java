package ua.nure.sliva.SummaryTask4.dao.mapper;

import ua.nure.sliva.SummaryTask4.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {
    public Order map(ResultSet rs) throws SQLException {
        int k = 0;
        Order order = new Order();
        order.setId(rs.getInt(++k));
        order.setPrice(rs.getDouble(++k));
        order.setUsersId(rs.getInt(++k));
        order.setOrders_status_id(rs.getInt(++k));
        order.setDate(rs.getTimestamp("date"));
        k++;
        order.setNote(rs.getString(++k));
        return order;
    }
}
