package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static ConnectionFactory instance;

    public ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {
        if (instance == null)
            instance = new ConnectionFactory();
        return instance;
    }

    public Connection get() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/wikimais";
        String user = "postgres";
        String password = "postgres";

        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
