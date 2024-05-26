import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewBooks extends JFrame {
    static Connection connection;
    public static DefaultTableModel tableModel;
    public static JScrollPane scrollPane;

    public ViewBooks() throws SQLException {
        connection = Connections.getConnection();
        if (connection != null) {

            String[] columnNames = {"ID", "Title", "Author", "Language", "Genre", "Quantity"};
            tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            String query = "SELECT * FROM books";
            tableModel = fetchBooks.fetchBooksList(tableModel, query, 1);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 10, 540, 290);
            this.add(scrollPane);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database");
        }

        this.setTitle("View Books");
        this.setLayout(null);
        this.setSize(600, 550);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
