import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public  class ViewBooks extends JFrame{
    static Connection connection;
    public static DefaultTableModel tableModel;
    public static JScrollPane scrollPane;
    public String[] titles; int t =0;
    public ViewBooks() throws SQLException {
        connection = Connections.getConnection();    
        if( connection != null) {
            System.out.println("Connected to the database for view books");

            String[] columnNames = {"ID", "Title", "Author", "Language", "genre", "quantity"};
            tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            String query = "SELECT * FROM books";
            tableModel = fetchBooks.fetchBooksList(tableModel, query);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20,10, 540, 290);

            
            this.add(scrollPane);
        } else {
            System.out.println("Failed to connect to the database");
        }

        this.setTitle("View Books");
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(600, 550);
        this.setVisible(true);
        // this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) throws SQLException {
        new ViewBooks();
    }
    
    
}
