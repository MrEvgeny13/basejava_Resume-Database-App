package com.evgeny13.basejava.util;

import com.evgeny13.basejava.exception.StorageException;
import com.evgeny13.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private static ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public interface Query<T> {
        T execute(Connection connection) throws SQLException;
    }

    public <T> T connectAndQuery(Query<T> query) {
        try (Connection connection = connectionFactory.getConnection()) {
            return query.execute(connection);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public PreparedStatement doStatement(Connection connection, String query,
                                         String... nameOfIndex) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < nameOfIndex.length; i++) {
            preparedStatement.setString(i + 1, nameOfIndex[i]);
        }

        return preparedStatement;
    }
}
