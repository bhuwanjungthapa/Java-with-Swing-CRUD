import java.sql.Statement;
public class Batch {
    public static void main(String[] args) throws Exception
    {
        DBConnection dbc = new DBConnection();

        dbc.con.setAutoCommit(false);
        Statement stmt = dbc.con.createStatement();
        stmt.addBatch("insert into students values('1','Shyam','Bachelor','100')");
        stmt.addBatch("insert into students values(2,'Ram','Master','90')");
        stmt.addBatch("insert into students values(3,'Muna','Phd','80')");
        stmt.addBatch("insert into students values(4,'Krishna','School','80')");
        stmt.executeBatch();//executing the batch
        dbc.con.commit();
        dbc.con.close();
    }
}
