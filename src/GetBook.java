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


public class GetBook extends ViewBooks {
    JFrame frame = new JFrame();
    public JLabel isbnLabel;
    public JTextField isbnField;  
    Connection connection;
    Statement statement;  
    int id;
    public GetBook(int id) throws SQLException {
        this.id = id;
        connection = Connections.getConnection();
        UIManager.put("Label.foreground", Color.BLACK);
        if( connection != null) {
            
            isbnLabel = new JLabel("ISBN");
            isbnField = new JTextField();
            isbnLabel.setBounds(100, 350, 50, 25);
            isbnField.setBounds(150, 350, 200, 25);
            this.add(isbnLabel);
            this.add(isbnField);
            JButton submitButton = new JButton("Get Book");
            submitButton.setBounds(150, 400, 100, 30);
            submitButton.addActionListener(e -> {
                new CheckAvailability(id).chk(isbnField.getText(), -1, "SELECT no_of_copies FROM books WHERE id = ?");
                try {
                    fetchBooks.fetchBooksList(tableModel, "SELECT * FROM books",1);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            });
            this.add(submitButton);
            
            
            this.setTitle("Get Book");

        } else {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database");
        }
        
    }
}
