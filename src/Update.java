import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Update {
    Connection connection = Connections.getConnection();
    int id;
    public Update(int id){
        this.id = id;
    }
    public void reduce(String isbn, int inc) throws SQLException {
        
        
        try {
            Connection connection = Connections.getConnection();
            String query = "UPDATE books SET no_of_copies = no_of_copies + ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inc);
            statement.setString(2, isbn);
            statement.executeUpdate();
            if( inc == 1) 
                query = "DELETE FROM issued_books WHERE b_id = ? AND user_id = ?";              
            else 
                query = "INSERT INTO issued_books (b_id, user_id) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, isbn);
            statement.setInt(2, id);
            statement.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
