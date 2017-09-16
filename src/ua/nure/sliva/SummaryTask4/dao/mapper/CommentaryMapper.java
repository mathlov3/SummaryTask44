package ua.nure.sliva.SummaryTask4.dao.mapper;

import ua.nure.sliva.SummaryTask4.entity.Commentary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentaryMapper {
    public Commentary map(ResultSet rs) throws SQLException {
        Commentary commentary = new Commentary();
        commentary.setId(rs.getInt("id"));
        commentary.setContent(rs.getString("content"));
        commentary.setProducts_id(rs.getInt("products_id"));
        commentary.setUsers_id(rs.getInt("users_id"));
        commentary.setDate(rs.getTimestamp("date"));
        return commentary;
    }
}
