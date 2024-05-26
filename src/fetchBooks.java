import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class fetchBooks {

    public static DefaultTableModel fetchBooksList(DefaultTableModel tableModel, String query, int view) throws SQLException {
        Connection connection = Connections.getConnection();
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            if (view == 0) {
                tableModel.setColumnIdentifiers(new Object[]{"ID", "Title", "Author", "Language", "Genre"});
            } else {
                tableModel.setColumnIdentifiers(new Object[]{"ID", "Title", "Author", "Language", "Genre", "Quantity"});
            }
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String language = resultSet.getString("language");
                String genre = resultSet.getString("genre");
                int quantity = resultSet.getInt("no_of_copies");
                Object[] rowData = {id, title, author, language, genre, quantity};
                tableModel.addRow(rowData);
            }     
        }catch(Exception e){
            e.printStackTrace();
        }
        return tableModel;
    }
}
