package br.edu.brazcubas.restaurante.database;

import java.sql.*;

public class PostgresConfig {
    private static final String url = "jdbc:postgresql://localhost:5432/restaurante";
    private static final String username = "postgres";
    private static final String password = "admin";

    public static Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}

