import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LibraryViewer extends JFrame implements ActionListener {

    JTextField issueField;
    JTextArea area;
    JButton displayBtn, updateBtn;

    public LibraryViewer() {

        setTitle("Library Viewer");
        setSize(500,400);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Issue ID:"));

        issueField = new JTextField(10);
        add(issueField);

        displayBtn = new JButton("Display");
        updateBtn = new JButton("Update");

        add(displayBtn);
        add(updateBtn);

        area = new JTextArea(10,30);
        add(area);

        displayBtn.addActionListener(this);
        updateBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int issueId = Integer.parseInt(issueField.getText());

        try {

            Connection conn = DBConnection.getConnection();

            // DISPLAY DETAILS
            if(e.getSource() == displayBtn) {

                PreparedStatement ps = conn.prepareStatement(
                    "SELECT i.issue_id, i.book_id, i.member_name, b.title, b.author " +
                    "FROM Issue_Table i JOIN Book b ON i.book_id = b.book_id " +
                    "WHERE i.issue_id = ?"
                );

                ps.setInt(1, issueId);

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    area.setText(
                        "Issue ID: " + rs.getInt("issue_id") +
                        "\nBook ID: " + rs.getInt("book_id") +
                        "\nMember: " + rs.getString("member_name") +
                        "\nTitle: " + rs.getString("title") +
                        "\nAuthor: " + rs.getString("author")
                    );
                } else {
                    area.setText("No record found");
                }
            }

            // UPDATE ISSUE TABLE
            if(e.getSource() == updateBtn) {

                String newName = JOptionPane.showInputDialog("Enter new member name:");

                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE Issue_Table SET member_name = ? WHERE issue_id = ?"
                );

                ps.setString(1, newName);
                ps.setInt(2, issueId);

                int rows = ps.executeUpdate();

                area.setText(rows + " record updated!");
            }

            conn.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LibraryViewer();
    }
}
