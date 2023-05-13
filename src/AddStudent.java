import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.*;
public class AddStudent extends JFrame implements ActionListener {
    JLabel lblstudentid, lblname, lblclass, lblmarks;

    JTextField txtstudentid, txtname, txtmarks;

    JComboBox cbclass;
    PreparedStatement pstmt;
    JButton btnsave;

    DBConnection dbc;

    public AddStudent()
    {
        dbc =  new DBConnection();
        setLayout(null);
        lblstudentid = new JLabel("Student ID");
        lblname = new JLabel("Name");
        lblclass = new JLabel("Class");
        lblmarks = new JLabel("Marks");

        txtstudentid = new JTextField(20);
        txtname = new JTextField(20);
        txtmarks = new JTextField(20);

        cbclass = new JComboBox();
        cbclass.addItem("+2");
        cbclass.addItem("Bachelor");
        cbclass.addItem("Master");

        btnsave = new JButton("Save");

        add(lblstudentid);
        lblstudentid.setBounds(50,50, 100,25);
        add(txtstudentid);
        txtstudentid.setBounds(150, 50, 100, 25);
        add(lblname);
        lblname.setBounds(50,100, 100,25);
        add(txtname);
        txtname.setBounds(150, 100, 100, 25);
        add(lblclass);
        lblclass.setBounds(50, 150, 100, 25);
        add(cbclass);
        cbclass.setBounds(150, 150, 100, 25);
        add(lblmarks);
        lblmarks.setBounds(50, 200, 100, 25);
        add(txtmarks);
        txtmarks.setBounds(150, 200, 100, 25);
        add(btnsave);
        btnsave.setBounds(150, 300, 100, 25);
        btnsave.addActionListener(this);
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnsave){
            try{
                pstmt=dbc.con.prepareStatement("insert into students values(?,?,?,?)");
                pstmt.setString(1, txtstudentid.getText());
                pstmt.setString(2, txtname.getText());
                pstmt.setString(3,cbclass.getSelectedItem().toString());
                pstmt.setString(4, txtmarks.getText());
                int result = pstmt.executeUpdate();
                if(result >0){
                    JOptionPane.showMessageDialog(null, "Record saved");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Unable to save");
                }
            }
            catch (Exception e3){
                e3.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new AddStudent();
    }
}