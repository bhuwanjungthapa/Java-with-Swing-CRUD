import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {
    Connection connection;
    Statement statement;

    public CreateTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/studentdb","root","");
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE students (id int, name varchar(20),class varchar(20), marks float);");
            System.out.println("Table created successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CreateTable();
    }
}
