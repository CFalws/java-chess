package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class JdbcTemplate {

    private final ConnectionPool connectionPool = new ConnectionPool();

    public <T> void update(String statement, T... parameters) {
        final Connection databaseConnection = connectionPool.getDatabaseConnection();

        try (final PreparedStatement updateStatement = databaseConnection.prepareStatement(statement)) {
            for (int i = 0; i < parameters.length; i++) {
                updateStatement.setObject(i + 1, parameters[i]);
            }
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
