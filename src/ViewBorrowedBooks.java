import java.sql.Connection;
import java.sql.SQLException;


public class ViewBorrowedBooks extends ViewBooks{
    Connection connection;
    public ViewBorrowedBooks(int id) throws SQLException {
        connection = Connections.getConnection();
        try{
        if(connection != null) {
            
            String query = "SELECT * FROM books WHERE id IN (SELECT b_id FROM issued_books WHERE user_id = "+id+")";
            
            tableModel = fetchBooks.fetchBooksList(tableModel, query);
            this.setTitle("View Borrowed Books");
            // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(600, 550);
            this.setVisible(true);
        
            this.setLocationRelativeTo(null);
        } else {
            System.out.println("Failed to connect to the database");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
}
    public static void main(String[] args) throws SQLException {
        new ViewBorrowedBooks(1);
    }

}
