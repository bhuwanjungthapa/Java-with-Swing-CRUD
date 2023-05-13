import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class DBConnection {
    public Connection con;
    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/studentdb","root","");
            System.out.println("'studentdb' database connection successful.");
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    public static void main(String[] args){
        new DBConnection();
    }
}
