package ua.nure.sliva.SummaryTask4.transaction;

import java.sql.SQLException;

public interface Transaction<T> {
    T execute() throws SQLException;
}
