import java.sql.*;
public class Transaction {
    public static void main(String[] args) throws Exception
    {

        DBConnection dbc = new DBConnection();

        dbc.con.setAutoCommit(false);
        Statement stmt = dbc.con.createStatement();
        stmt.executeUpdate("insert into students values(5,'aa','93','20')");
        stmt.executeUpdate("insert into students values(6,'vvv','20','82')");
        stmt.executeUpdate("insert into students values(7,'dd','22','20')");
        stmt.executeUpdate("insert into students values(8,'ff','331','36')");
        dbc.con.commit();
        dbc.con.close();
    }
}
