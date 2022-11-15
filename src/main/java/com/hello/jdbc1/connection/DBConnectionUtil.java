package com.hello.jdbc1.connection;

import static com.hello.jdbc1.connection.ConnectionConst.PASSWORD;
import static com.hello.jdbc1.connection.ConnectionConst.URL;
import static com.hello.jdbc1.connection.ConnectionConst.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, class={}", connection, connection.getClass());

            return connection;
        } catch (SQLException e) {
            // check exception을 runtime exception으로 던진다
            throw new IllegalStateException(e);
        }
    }
}
