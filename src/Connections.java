import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	public static java.sql.Connection getConnection() {
		 String url = "jdbc:mysql://localhost:3306/library_management_system";
         String user = "root";
         String password = "root";
         try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}