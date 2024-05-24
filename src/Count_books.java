import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Count_books extends Check_Availability{
    public Count_books(int id) throws SQLException {
        super(id);
    }

    public void reduce(String isbn, int inc) throws SQLException {
        
        
        try {
            Connection connection = Connections.getConnection();
            String query = "UPDATE books SET no_of_copies = no_of_copies - ? WHERE isbn = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, inc);
            statement.setString(2, isbn);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
