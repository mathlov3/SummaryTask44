package ua.nure.sliva.SummaryTask4.dao.mysql;

import ua.nure.sliva.SummaryTask4.constants.Sql;
import ua.nure.sliva.SummaryTask4.dao.CommentaryDAO;
import ua.nure.sliva.SummaryTask4.dao.mapper.CommentaryMapper;
import ua.nure.sliva.SummaryTask4.entity.Commentary;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentaryDAOImpl implements CommentaryDAO {
    private CommentaryMapper commentaryMapper = new CommentaryMapper();

    @Override
    public Commentary getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int create(Commentary entity) {
        Connection connection = ThreadLocaleHandler.getConnection();
        int id = 0;
        try(PreparedStatement ps = connection.prepareStatement(Sql.CREATE_COMMENTARY,PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            ps.setInt(++k,entity.getProducts_id());
            ps.setString(++k,entity.getContent());
            if(entity.getUsers_id()!=null){
                ps.setInt(++k, entity.getUsers_id());
            } else {
                ps.setNull(++k,java.sql.Types.INTEGER);
            }
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
    public int update(Commentary entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Commentary entity) {
        throw new UnsupportedOperationException();
    }
    @Override
    public List<Commentary> getCommentariesByProductId(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        List<Commentary> commentaries = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_COMMENTARIES_BY_PRODUCT_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                commentaries.add(commentaryMapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException(e);
        }
        return commentaries;
    }
}
