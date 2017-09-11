package ua.nure.sliva.SummaryTask4.dao.mapper;

import ua.nure.sliva.SummaryTask4.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public void unMap(User user, PreparedStatement ps) throws SQLException {
        User u = new User();
        int k = 0;
        ps.setInt(++k, user.getId());
        ps.setString(++k, user.getLogin());
        ps.setString(++k, user.getPassword());
        ps.setString(++k, user.getName());
        ps.setString(++k, user.getEmail());
        ps.setInt(++k, Integer.parseInt(user.getEmail()));
    }

    public User map(ResultSet rs) throws SQLException {
        User user = new User();
        int k = 0;
        user.setId(rs.getInt(++k));
        user.setLogin(rs.getString(++k));
        user.setPassword(rs.getString(++k));
        user.setName(rs.getString(++k));
        user.setEmail(rs.getString(++k));
        user.setRole(rs.getInt(++k));
        return user;
    }
}
