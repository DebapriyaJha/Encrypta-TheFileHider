package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    static Connection connection;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/FileHider?useSSL=false","root","Abhi@2003");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection done");
       return connection;
    }
    public static void closeConnection() {
        if(connection!=null)
            try {
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
    }



}
