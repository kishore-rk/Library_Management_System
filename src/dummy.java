import java.sql.Connection;
import java.sql.Statement;


public class dummy {
    public dummy() throws Exception{
        Connection connection = Connections.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into users values(0,'admin', 'admin',8234598966,0)");
        statement.execute("Insert into users values(1,'student1', 'student1',9357924608,0)");
        statement.execute("Insert into users values(2,'student2', 'student2',9237848356,0)");
        statement.execute("Insert into users values(3,'student3', 'student3',8774556772,0)");
        statement.execute("Insert into users values(4,'student4', 'student4',8675467788,0)");
        statement.execute("Insert into users values(5,'student5', 'student5',9779506588,0)");
    }
    public static void main(String[] args) throws Exception {
        new dummy();
    }
}
