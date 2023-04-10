package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class JdbcTemplate {

    private final Connection databaseConnection = new ConnectionPool().getDatabaseConnection();

    @SafeVarargs
    public final <T> void update(String statement, T... parameters) {
        try (final PreparedStatement updateStatement = databaseConnection.prepareStatement(statement)) {
            setParameters(updateStatement, parameters);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<List<String>> query(final String query, T... parameters) {
        try (final PreparedStatement queryStatement = databaseConnection.prepareStatement(query)) {
            setParameters(queryStatement, parameters);
            final ResultSet resultSet = queryStatement.executeQuery();
            return getResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SafeVarargs
    public final <T> void setParameters(PreparedStatement preparedStatement, T... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }
    }

    private List<List<String>> getResult(final ResultSet resultSet) throws SQLException {
        List<List<String>> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(getRow(resultSet));
        }
        return result;
    }

    private List<String> getRow(final ResultSet resultSet) throws SQLException {
        List<String> row = new ArrayList<>();
        final ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            row.add(resultSet.getString(i + 1));
        }
        return row;
    }
}
