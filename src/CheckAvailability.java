import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CheckAvailability {
    int id;
    public CheckAvailability(int id) {
        this.id = id;
    }
    void chk(String isbn,int get, String query) {
        try {
            Connection connection = Connections.getConnection();    
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if ( resultSet.next()) {
                if(get == -1 && resultSet.getInt("no_of_copies") == 0) {
                    JOptionPane.showMessageDialog(null, "Book not available");
                    return;
                }
                
                new Update(id).reduce(isbn,get);
                if(get == -1) {
                    JOptionPane.showMessageDialog(null, "Book issued successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Book returned successfully");
                }
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());  
        }
        
        JOptionPane.showMessageDialog(null, "Book not found");
        
    }
}