package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface JdbcStrategy {
    PreparedStatement makePreparedStatement(Connection con) throws SQLException;
}
