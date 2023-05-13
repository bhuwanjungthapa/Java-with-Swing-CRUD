import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class UpdateStudent extends JFrame implements ActionListener {
    JLabel lblid, lblname, lblclass, lblmarks;
    JTextField txtname, txtclass, txtmarks;
    JComboBox cbid;
    JButton btnupdate;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    DBConnection dbc;

    public UpdateStudent() {
        dbc = new DBConnection();

        lblid = new JLabel("Student ID");
        lblname = new JLabel("Name");
        lblmarks = new JLabel("Marks");
        lblclass = new JLabel("Class");

        txtname = new JTextField();
        txtmarks=new JTextField();
        txtclass=new JTextField();

        cbid = new JComboBox();

        btnupdate = new JButton("Update");

        setLayout(null);
        add(lblid);
        lblid.setBounds(50, 50, 100, 25);
        add(cbid);
        cbid.setBounds(150, 50, 100, 25);

        add(lblname);
        lblname.setBounds(50, 100, 100, 25);
        add(txtname);
        txtname.setBounds(150, 100, 100, 25);

        add(lblclass);
        lblclass.setBounds(50, 150, 100, 25);
        add(txtclass);
        txtclass.setBounds(150, 150, 100, 25);

        add(lblmarks);
        lblmarks.setBounds(50, 200, 100, 25);
        add(txtmarks);
        txtmarks.setBounds(150, 200, 100, 25);


        add(btnupdate);
        btnupdate.addActionListener(this);
        btnupdate.setBounds(100, 250, 75, 25);
        try {
            stmt = dbc.con.createStatement();
            rs = stmt.executeQuery("select * from students");
            while (rs.next()) {
                cbid.addItem(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        cbid.addActionListener(this);
        setVisible(true);
        setSize(300, 350);
        setTitle("Update Students");//set the title
        setLocationRelativeTo(null);//frame locate according to screen resolution
    }
    public static void main(String[] args)
    {
        new UpdateStudent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbid) {
            try {
                pstmt = dbc.con.prepareStatement("select * from students where id=?");
                pstmt.setString(1, cbid.getSelectedItem().toString());
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    txtname.setText(rs.getString(2));
                    txtclass.setText(rs.getString(3));
                    txtmarks.setText(rs.getString(4));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (e.getSource() == btnupdate) {
            try {
                pstmt = dbc.con.prepareStatement("update students set name=?,class=?,marks=? where id=?");
                pstmt.setString(1, txtname.getText());
                pstmt.setString(2, txtclass.getText());
                pstmt.setString(3, txtmarks.getText());
                pstmt.setString(4,cbid.getSelectedItem().toString());
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Student Updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Student is not Updated");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
