import java.awt.Color;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.sql.ResultSet;

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
            System.out.println("Connected to the database");
            
            isbnLabel = new JLabel("ISBN");
            isbnField = new JTextField();
            isbnLabel.setBounds(100, 350, 50, 25);
            isbnField.setBounds(150, 350, 200, 25);
            this.add(isbnLabel);
            this.add(isbnField);
            JButton submitButton = new JButton("Get Book");
            submitButton.setBounds(150, 400, 100, 30);
            submitButton.addActionListener(e -> {
                chk(isbnField.getText());

            });
            this.add(submitButton);
            
            
            this.setTitle("Get Book");

        } else {
            System.out.println("Failed to connect to the database");
        }
        
    }
    private void chk(String isbn) {
        String query = "SELECT * FROM books WHERE id= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if(resultSet.getInt("no_of_copies") == 0) {
                    JOptionPane.showMessageDialog(null, "Book not available");
                    return;
                }
                
                reduce(isbn);
                query = "Insert into issued_books (user_id, b_id) values (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.setString(2, isbn);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Book issued successfully");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());  
        }
        JOptionPane.showMessageDialog(null, "Book not found");
        
    }
    private void reduce(String isbn) throws SQLException {
        String query = "UPDATE books SET no_of_copies = no_of_copies - 1 WHERE id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, isbn);
            statement.executeUpdate();
            fetchBooks.fetchBooksList(tableModel, "SELECT * FROM books");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        try {
            new GetBook(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
