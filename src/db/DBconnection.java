package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static DBconnection dBconnection;
    private static Connection connection;

    private DBconnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static DBconnection getInstance(){
        return (dBconnection == null) ? dBconnection =new DBconnection() :dBconnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
