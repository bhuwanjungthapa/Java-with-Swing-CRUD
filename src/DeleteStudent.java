import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class DeleteStudent extends JFrame implements ActionListener {

    JLabel lblstudentid, lblname ;

    JComboBox cbstudentid;

    JTextField txtname;

    JButton btndelete;
    DBConnection dbc;

    Statement stmt;
    PreparedStatement pstmt;

    ResultSet rs;
    public DeleteStudent(){
        dbc = new DBConnection();
        setLayout(null);

        lblstudentid = new JLabel("Student ID");
        lblname = new JLabel("Student Name");

        cbstudentid = new JComboBox();

        txtname = new JTextField();

        btndelete = new JButton("Delete");

        add(lblstudentid);
        lblstudentid.setBounds(50, 50, 100, 25);
        add(lblname);
        lblname.setBounds(150, 50, 100, 25);

        add(cbstudentid);
        cbstudentid.setBounds(50, 100, 100, 25);
        add(txtname);
        txtname.setBounds(150, 100, 100, 25);

        add(btndelete);
        btndelete.setBounds(100, 300, 80, 25);
        btndelete.addActionListener(this);
        try {
            stmt = dbc.con.createStatement();
            rs = stmt.executeQuery("select * from students");
            while (rs.next()){
                cbstudentid.addItem(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cbstudentid.addActionListener(this);
        setSize(300, 420);
        setResizable(false);  //we can not change the frame size.
        setTitle("Delete Data");
        setLocation(585,260);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        DeleteStudent d = new DeleteStudent();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==cbstudentid){
            try {
                pstmt = dbc.con.prepareStatement("select * from students where id = ?");
                pstmt.setString(1, cbstudentid.getSelectedItem().toString());
                rs = pstmt.executeQuery();
                if (rs.next()){
                    txtname.setText(rs.getString(2));
                }
            }catch (Exception ex){
                ex.getStackTrace();
            }

        }
        if(e.getSource()==btndelete){
            try {
                pstmt = dbc.con.prepareStatement("delete from students where id = ?");
                pstmt.setString(1, cbstudentid.getSelectedItem().toString());
                int result = pstmt.executeUpdate();
                if (result > 0){
                    JOptionPane.showMessageDialog(null,"Record deleted successfully");
                }else {
                    JOptionPane.showMessageDialog(null, "Unsuccessfull");
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }

        }
    }
}
