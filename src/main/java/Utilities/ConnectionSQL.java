package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Bruce Mota
 */

//Metodo dos Drivers para fazer conex�o com banco de dados
public class ConnectionSQL {
    //parametros dos Drivers
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/AgendaTarefas";
    public static final String USER = "root";
    public static final String PASS = "";
    //parametro de conex�o primaria
    public static java.sql.Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar conex�o com banco de dados", ex);
        }
    }
    //fechar conex�o com banco de dados
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar conex�o com banco de dados", ex);
        }
    }
    //segunda tentativa de fechar conex�o com bando de dados
    public static void closeConnection(Connection connection, PreparedStatement stmt) {
        closeConnection(connection);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar conex�o com banco de dados", ex);
        }
    }
}