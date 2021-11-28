package com.evgeny13.basejava.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlConnection<T> {
    T execute(Connection conn) throws SQLException;
}