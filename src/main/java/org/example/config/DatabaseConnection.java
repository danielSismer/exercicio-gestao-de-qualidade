package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final static String URL = "jdbc:h2:mem:teste;";
    private final static String USER = "sa";
    private final static String PASSWORD = "";

    public static Connection getConenction() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) throws SQLException{
        try{
            Connection conn = getConenction();

            if(conn != null){
                System.out.println("Conectado com sucesso!!!");
            } else {
                System.out.println("Falha ao conectar!!!");
            }
        } catch (SQLException e ){
            e.printStackTrace();
        }
    }

}
