package ua.nure.sliva.SummaryTask4.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ua.nure.sliva.SummaryTask4.constants.Sql;
import ua.nure.sliva.SummaryTask4.dao.mapper.ProductMapper;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private  ProductMapper productMapper = new ProductMapper();
    @Override
    public Product getById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        Product product = null;
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_PRODUCT_BY_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ProductMapper productMapper = new ProductMapper();
                product = productMapper.map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return product;
    }

    @Override
    public int create(Product entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try(PreparedStatement ps = connection.prepareStatement(Sql.CREATE_PRODUCT,PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setString(++k,entity.getName());
            ps.setString(++k,entity.getDescription());
            ps.setDouble(++k,entity.getPrice());
            ps.setInt(++k,entity.getCount());
            ps.setInt(++k,entity.getCategoryId());
            ps.setBytes(++k,entity.getImg());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new DBException(e);
        }
        return id;
    }

    @Override
    public int update(Product entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try(PreparedStatement ps = connection.prepareStatement(Sql.UPDATE_PRODUCT,PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setInt(++k,entity.getId());
            ps.setString(++k,entity.getName());
            ps.setString(++k,entity.getDescription());
            ps.setDouble(++k,entity.getPrice());
            ps.setInt(++k,entity.getCount());
            ps.setInt(++k,entity.getCategoryId());
            ps.setBytes(++k,entity.getImg());
            ps.setInt(++k,entity.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return id;
    }

    @Override
    public int delete(Product entity) {
        return 0;
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Product> products = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_PRODUCTS_BY_CATEGORY_ID)) {
            ps.setInt(1,categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                products.add(productMapper.map(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DBException(e);
        }
        return products;
    }

    @Override
    public List<Category> getAllCategory() {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_ALL_CATEGORIES)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
                categories.add(category);
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw  new DBException(e);
        }
        return categories;
    }

    @Override
    public int getCountProductsByCategoryId(int categoryId) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int count = 0;
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_PRODUCT_COUNT_BY_CATEGORY)) {
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
            throw new DBException(e);
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
            throw new DBException(e);
        }
        return count;
    }

    @Override
    public List<Product> getProductsByOrderId(int id) {
        List<Product> products = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_PRODUCTS_BY_ORDER_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()){
                products.add(productMapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return products;
    }

    @Override
    public List<Product> getNewProducts() {
        List<Product> products = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_NEW_FOUR_PRODUCTS)){
            ResultSet rs = ps.executeQuery();
            ProductMapper productMapper = new ProductMapper();
            while (rs.next()){
                products.add(productMapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return products;
    }

    @Override
    public void voteForProduct(int pId, int uId, int vote) {
        Connection connection = ThreadLocaleHandler.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(Sql.VOTE_FOR_PRODUCT)) {
            ps.setInt(1,uId);
            ps.setInt(2,pId);
            ps.setInt(3,vote);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public int getProductVote(int pId) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int vote = 0;
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_PRODUCT_VOTE)) {
            ps.setInt(1,pId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                vote = (int) (rs.getDouble(1)+0.5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return vote;
    }

    @Override
    public List<byte[]> getImagesById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<byte[]> images = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_IMAGES_BY_PRODUCT_ID)) {
            ps.setInt(1,id);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                images.add(rs.getBytes(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return images;
    }

    @Override
    public int addProductImage(byte[] image, int productId) {
        Connection connection =ThreadLocaleHandler.getConnection();
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(Sql.ADD_IMAGE_FOR_PRODUCT,PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setBytes(1,image);
            ps.setInt(2,productId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                id= rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return id;
    }


    @Override
    public List<Product> getList(int start, int end, boolean ascending, String orderColumn,int category) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Product> products = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_PRODUCTS_BY_RESTRICTS)) {
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
            throw new DBException(e);
        }
        return products;
    }
}
