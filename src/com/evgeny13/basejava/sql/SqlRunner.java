package com.evgeny13.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlRunner<T> {
    T execute(PreparedStatement st) throws SQLException;
}
