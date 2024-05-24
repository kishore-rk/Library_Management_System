import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class fetchBooks {
    public DefaultTableModel tableModel;
    
    public static DefaultTableModel fetchBooksList( DefaultTableModel tableModel, String query) throws SQLException{
        Connection connection = Connections.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        tableModel.setRowCount(0);
        while (resultSet.next()) {
            String id = ( resultSet.getString("id"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String language = resultSet.getString("language");
            String genre = resultSet.getString("genre");
            int quantity = resultSet.getInt("no_of_copies");
            // titles[t++] = title;
            Object[] rowData = {id, title, author, language, genre, quantity};
            tableModel.addRow(rowData);            
        }
        return tableModel;
    }
}
