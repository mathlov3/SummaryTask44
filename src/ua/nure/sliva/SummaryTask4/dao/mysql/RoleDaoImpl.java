package ua.nure.sliva.SummaryTask4.dao.mysql;

import ua.nure.sliva.SummaryTask4.constants.Sql;
import ua.nure.sliva.SummaryTask4.dao.RoleDao;
import ua.nure.sliva.SummaryTask4.entity.Role;
import ua.nure.sliva.SummaryTask4.exception.DBException;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {

    @Override
    public Role getById(int id) {
        Connection connection = ThreadLocaleHandler.getConnection();
        Role role = null;
        try(PreparedStatement ps = connection.prepareStatement(Sql.GET_ROLE_BY_ID)) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                role = new Role();
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
        return role;
    }

    @Override
    public int create(Role entity) {
        return 0;
    }

    @Override
    public int update(Role entity) {
        return 0;
    }

    @Override
    public int delete(Role entity) {
        return 0;
    }
}
