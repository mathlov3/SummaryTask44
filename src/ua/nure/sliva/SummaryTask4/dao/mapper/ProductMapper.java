package ua.nure.sliva.SummaryTask4.dao.mapper;

import ua.nure.sliva.SummaryTask4.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper {
    public Product map(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setCount(rs.getInt("count"));
        product.setCategoryId(rs.getInt("categories_id"));
        product.setPrice(rs.getDouble("price"));
        return product;
    }
}
