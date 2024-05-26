import java.awt.Color;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ReturnBook extends ViewBorrowedBooks{
    JFrame frame = new JFrame();
    public JLabel isbnLabel;
    public JTextField isbnField;  
    Connection connection;
    Statement statement;  
    public ReturnBook(int id) throws SQLException {
       super(id);
    
        
        connection = Connections.getConnection();
        UIManager.put("Label.foreground", Color.BLACK);
        if( connection != null) {
            
            isbnLabel = new JLabel("ISBN");
            isbnField = new JTextField();
            isbnLabel.setBounds(100, 350, 50, 25);
            isbnField.setBounds(150, 350, 200, 25);
            this.add(isbnLabel);
            this.add(isbnField);
            JButton submitButton = new JButton("Return");
            submitButton.setBounds(150, 400, 100, 30);
            submitButton.addActionListener(e -> {
                try {
                    new CheckAvailability(id).chk(isbnField.getText(), 1, "SELECT * FROM issued_books WHERE b_id = ? and user_id = "+id);
                    fetchBooks.fetchBooksList(tableModel, "SELECT * FROM books WHERE id IN (SELECT b_id FROM issued_books WHERE user_id = " + id + ")",0);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                

            });
            this.setTitle("Return Book");
            this.add(submitButton);
            
        } else {        
            JOptionPane.showMessageDialog(null, "Failed to connect to the database");
        }
    }
}
