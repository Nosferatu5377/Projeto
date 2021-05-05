package Livraria.Livros;

import java.sql.*;

//Codigo implementado que realiza a conexão com o banco de dados MySQL local.

public class DBConnection {

    private static final String URL_MYSQL = "jdbc:mysql://localhost/livros?createDatabaseIfNotExist=true";
    private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados");
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;

        String sql = "CREATE TABLE IF NOT EXISTS LIVROS (" +
                "ID bigint(20) NOT NULL AUTO_INCREMENT, " +
                "EDITORA varchar(50) NOT NULL, " +
                "TITULO varchar(50) NOT NULL, " +
                "ISBN varchar(50) NOT NULL, " +
                "PRIMARY KEY (ID)" +
                ") ENGINE=InnoDB";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
}

