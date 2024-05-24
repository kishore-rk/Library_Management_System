import java.awt.Color;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ReturnBook extends ViewBorrowedBooks{
    JFrame frame = new JFrame();
    public JLabel isbnLabel;
    public JTextField isbnField;  
    Connection connection;
    Statement statement;  
    int id;
    public ReturnBook(int id) throws SQLException {
        super(id); 
    
        this.id = id; // Explicitly invoke the constructor of the superclass
        
        this.id = id;
        connection = Connections.getConnection();
        UIManager.put("Label.foreground", Color.BLACK);
        if( connection != null) {
            System.out.println("Connected to the database");
            
            isbnLabel = new JLabel("ISBN");
            isbnField = new JTextField();
            isbnLabel.setBounds(100, 350, 50, 25);
            isbnField.setBounds(150, 350, 200, 25);
            this.add(isbnLabel);
            this.add(isbnField);
            JButton submitButton = new JButton("Return Book");
            submitButton.setBounds(150, 400, 100, 30);
            submitButton.addActionListener(e -> {
                String query = "SELECT * FROM books WHERE id= ?";
                new Check_Availability(id).chk(isbnField.getText(), query, -1);
                try {
                    fetchBooks.fetchBooksList(tableModel, "SELECT * FROM books");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            });
            this.add(submitButton);
            
    }

}}
