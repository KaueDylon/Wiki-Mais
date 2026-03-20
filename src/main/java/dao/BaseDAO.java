package dao;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseDAO {
    protected Connection conn() throws SQLException{

        return ConnectionFactory.getInstance().get();
    }

}
