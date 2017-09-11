package ua.nure.sliva.SummaryTask4.transaction;

import java.sql.Connection;

public class ThreadLocaleHandler {
    public static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static void setConnecion(Connection connection){
        threadLocal.set(connection);
    }

    public static Connection getConnection(){
        return threadLocal.get();
    }
}
