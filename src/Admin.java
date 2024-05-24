import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Admin extends JFrame implements ActionListener{
    public JTextField isbnField, titleField, authorField, languageField, genreField, copiesField;
    public JLabel isbnLabel, titleLabel, authorLabel, languageLabel, genreLabel, copiesLabel;
    public JButton submitButton;

    public Admin() {
        
        // UIManager.put("Label.foreground", Color.BLACK);
        isbnLabel = new JLabel("ISBN");
        isbnField = new JTextField();
        isbnLabel.setBounds(50, 50, 100, 25);
        isbnField.setBounds(150, 50, 200, 25);
        this.add(isbnLabel);
        this.add(isbnField);

        titleLabel = new JLabel("Title");
        titleField = new JTextField();
        titleLabel.setBounds(50, 100, 100, 25);
        titleField.setBounds(150, 100, 200, 25);
        this.add(titleLabel);
        this.add(titleField);

        authorLabel = new JLabel("Author");
        authorField = new JTextField();
        authorLabel.setBounds(50, 150, 100, 25);
        authorField.setBounds(150, 150, 200, 25);
        this.add(authorLabel);
        this.add(authorField);

        languageLabel = new JLabel("Language");
        languageField = new JTextField();
        languageLabel.setBounds(50, 200, 100, 25);
        languageField.setBounds(150, 200, 200, 25);

        genreLabel = new JLabel("Genre");
        genreField = new JTextField();
        genreLabel.setBounds(50, 250, 100, 25);
        genreField.setBounds(150, 250, 200, 25);

        copiesLabel = new JLabel("Copies");
        copiesField = new JTextField();
        copiesLabel.setBounds(50, 300, 100, 25);
        copiesField.setBounds(150, 300, 200, 25);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBounds(150, 350, 100, 30);
        
        this.add(languageLabel);
        this.add(languageField);
        this.add(genreLabel);
        this.add(genreField);
        this.add(copiesLabel);
        this.add(copiesField);
        this.add(submitButton);
        // this.add(resetButton);
       
        this.setLayout(new GridLayout(7, 2));
        this.add(isbnLabel);
        this.add(isbnField);
        this.setTitle("Admin");
        
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(null);
        this.setSize(650, 450);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); 
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                connection = Connections.getConnection();
                if (connection != null) {
                    System.out.println("Connected to the database for adding books");
                    String isbn = isbnField.getText();
                    String title = titleField.getText();
                    String author = authorField.getText();
                    String language = languageField.getText();
                    String genre = genreField.getText();
                    int copies = Integer.parseInt(copiesField.getText());
        
                    if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || language.isEmpty() || genre.isEmpty() || copiesField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please fill all the fields");
                        return;
                    }
        
                    if (copies < 0) {
                        JOptionPane.showMessageDialog(this, "Copies cannot be negative");
                        return;
                    }
        
                    // Check if ISBN already exists
                   // Check if ISBN already exists
                    String query = "SELECT COUNT(*) FROM books WHERE id= ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, isbn);
                    resultSet = statement.executeQuery();
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(this, "Book with this ISBN already exists");
                        return;
                    }

        
                    // Insert the book
                    String sql = "INSERT INTO books (id, title, author, language, genre, no_of_copies, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, isbn);
                    statement.setString(2, title);
                    statement.setString(3, author);
                    statement.setString(4, language);
                    statement.setString(5, genre);
                    statement.setInt(6, copies);
                    statement.setInt(7, copies);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Book added successfully!");
                } else {
                    System.out.println("Failed to connect to the database");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            } finally {
                // Close resources in finally block to ensure they are always closed
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    public static void main(String[] args) {
        new Admin();
    }
}
