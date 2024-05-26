import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginInfo {
    String username;   
    String password;
    public LoginInfo connectToDatabase;
    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public int connectToDatabase() {
        Connection connection = Connections.getConnection();
        if( connection != null) {
            System.out.println("Connected to the database");
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '"+username+"' AND password = '"+password+"'");
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
                return -1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database");
        }
        return -1;
    }
}