package br.edu.brazcubas.restaurante.database;

import java.sql.*;

public class PostgresConfig {
    private static final String url = "jdbc:postgresql://localhost:8080/sales";
    private static final String username = "admin";
    private static final String password = "admin";

    public static Connection conectarDB() throws SQLException{
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}

