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

    /*public static void testInsert() throws SQLException {
        try {
            Connection conn = conectarDB();
            String query = "insert into cliente values (default, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(2, "123.456.789-12");
            stmt.setString(1, "Rafael");
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }*/
}

