import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = Connections.getConnection();
        if( connection != null) {
            System.out.println("Connected to the database");
            new Login();
        } else {
            System.out.println("Failed to connect to the database");
        }
            
    }
}
