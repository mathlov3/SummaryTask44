package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.constants.R;
import ua.nure.sliva.SummaryTask4.dao.mapper.ProductMapper;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public Product getById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        Product product = null;
        try(PreparedStatement ps = connection.prepareStatement(R.GET_PRODUCT_BY_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ProductMapper productMapper = new ProductMapper();
                product = productMapper.map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return product;
    }

    @Override
    public int create(Product entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try(PreparedStatement ps = connection.prepareStatement(R.CREATE_PRODUCT,PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setString(++k,entity.getName());
            ps.setString(++k,entity.getDescription());
            ps.setDouble(++k,entity.getPrice());
            ps.setInt(++k,entity.getCount());
            ps.setInt(++k,entity.getCategoryId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new UnsupportedOperationException(e);
        }
        return id;
    }

    @Override
    public int update(Product entity) {
        return 0;
    }

    @Override
    public int delete(Product entity) {
        return 0;
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Product> products = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(R.GET_PRODUCTS_BY_CATEGORY_ID)) {
            ps.setInt(1,categoryId);
            ResultSet rs = ps.executeQuery();
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()){
                products.add(productMapper.map(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return products;
    }

    @Override
    public List<Category> getAllCategory() {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(R.GET_ALL_CATEGORIES)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw  new UnsupportedOperationException(e);
        }
        return categories;
    }

    @Override
    public int getCountProductsByCategoryId(int categoryId) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int count = 0;
        try(PreparedStatement ps = connection.prepareStatement(R.GET_PRODUCT_COUNT_BY_CATEGORY)) {
            ps.setInt(1,categoryId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Product> getProductsBySql(String sql, int start) {
        List<Product> products = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,start);
            ResultSet rs = ps.executeQuery();
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()){
               products.add(productMapper.map(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return products;
    }

    @Override
    public int getCountProductsBySql(String sql) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int count = 0;
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return count;
    }

    @Override
    public List<Product> getProductsByOrderId(int id) {
        List<Product> products = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(R.GET_PRODUCTS_BY_ORDER_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()){
                products.add(productMapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return products;
    }


    @Override
    public List<Product> getList(int start, int end, boolean ascending, String orderColumn,int category) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Product> products = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(R.GET_PRODUCTS_BY_RESTRICTS)) {
            ps.setInt(1,category);
            ps.setInt(2,start);
            ps.setInt(3,end);
            ResultSet rs = ps.executeQuery();
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()){
                products.add(productMapper.map(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return products;
    }
}
