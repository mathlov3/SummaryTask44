package ua.nure.sliva.SummaryTask4.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionPoolMysql implements TRPool {
    private DataSource ds;

    public TransactionPoolMysql(DataSource ds){
        this.ds = ds;
    }

    public <T> T execute(Transaction<T> transaction){
        Connection connection = null;

        T result = null;
        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            ThreadLocaleHandler.setConnecion(connection);
            result = transaction.execute();
            connection.commit();
        } catch (SQLException e){
            rollbackConnection(connection);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public void closeConnection(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void rollbackConnection(Connection connection){
        try {
            if (connection != null){
                connection.rollback();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
