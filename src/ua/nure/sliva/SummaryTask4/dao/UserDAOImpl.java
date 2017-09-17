package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.constants.Sql;
import ua.nure.sliva.SummaryTask4.dao.mapper.UserMapper;
import ua.nure.sliva.SummaryTask4.entity.User;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private UserMapper userMapper = new UserMapper();
    @Override
    public User getByLogin(String login) {
        Connection connection = ThreadLocaleHandler.getConnection();
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_USER_BY_LOGIN)) {
            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = userMapper.map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ThreadLocaleHandler.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_ALL_USERS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                users.add(userMapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return users;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        Connection connection = ThreadLocaleHandler.getConnection();
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_USER_BY_LOGIN_AND_PASSWORD)){
            ps.setString(1,login);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = userMapper.map(rs);
            }

        } catch (SQLException e){
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return user;
    }

    @Override
    public User getById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        User user = null;
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_USER_BY_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = userMapper.map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return user;
    }

    @Override
    public int create(User user) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int key = 0;
        try(PreparedStatement ps = connection.prepareStatement(Sql.CREATE_USER,PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setString(++k,user.getLogin());
            ps.setString(++k,user.getPassword());
            ps.setString(++k,user.getName());
            ps.setString(++k,user.getEmail());
            ps.setInt(++k,user.getRole());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return key;
    }

    @Override
    public int update(User entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int key = 0;
        try(PreparedStatement ps = connection.prepareStatement(Sql.UPDATE_USER,PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setInt(++k,entity.getId());
            ps.setString(++k,entity.getLogin());
            ps.setString(++k,entity.getPassword());
            ps.setString(++k,entity.getName());
            ps.setString(++k,entity.getEmail());
            ps.setInt(++k,entity.getRole());
            ps.setInt(++k,entity.isBan()?1:0);
            ps.setInt(++k,entity.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return key;
    }

    @Override
    public int delete(User entity) {
        return 0;
    }
}
