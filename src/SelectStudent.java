import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectStudent extends JFrame implements ActionListener {
    JLabel label;
    JComboBox cbsearch;
    JTextField txtfield;
    JButton btnsearch;

    static JTable table;

    DBConnection dbConnection;
    PreparedStatement preparedStatement;
    ResultSet rs;
    static DefaultTableModel model;

    public SelectStudent() {
        setLayout(null);
        setSize(600, 600);
        setVisible(true);
        setTitle("Search");

        label = new JLabel("Search From");
        cbsearch = new JComboBox();
        txtfield = new JTextField();
        btnsearch = new JButton("Search");

        add(label);
        label.setBounds(20, 10, 100, 30);

        add(cbsearch);
        cbsearch.setBounds(100, 10, 100, 30);
        cbsearch.addItem("StudentID");
        cbsearch.addItem("Name");
        cbsearch.addItem("Class");
        cbsearch.addItem("Marks");

        add(txtfield);
        txtfield.setBounds(200, 10, 120, 30);
        add(btnsearch);
        btnsearch.setBounds(340, 10, 100, 30);
        btnsearch.addActionListener(this);
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("StudentID ");
        model.addColumn("Name");
        model.addColumn("Class");
        model.addColumn("Marks");
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnsearch) {
            try {
                dbConnection = new DBConnection();

                String selected = (String) cbsearch.getSelectedItem();


                if (selected.equals("StudentID")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM students WHERE id = ?");
                    preparedStatement.setString(1, txtfield.getText());
                } else if (selected.equals("Name")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM students WHERE name = ?");
                    preparedStatement.setString(1, txtfield.getText());

                } else if (selected.equals("Class")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM students WHERE class = ?");
                    preparedStatement.setString(1, txtfield.getText());

                } else {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM students WHERE marks = ?");
                    preparedStatement.setString(1, txtfield.getText());

                }
                rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    do {
                        model.addRow(new Object[] {
                                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                        });
                    } while (rs.next());

                } else {
                    JOptionPane.showMessageDialog(null, "No data found");
                }
                int vertical_scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
                int horizontal_scroll = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

                JScrollPane scrollPane = new JScrollPane((table), vertical_scroll, horizontal_scroll);

                add(scrollPane);
                scrollPane.setBounds(10, 170, 500, 200);


            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new SelectStudent();
    }
}