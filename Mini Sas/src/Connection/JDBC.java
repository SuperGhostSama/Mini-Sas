package Connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    private static Connection con;
    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio", "root", "");
            System.out.println(con);
            System.out.println("Connection Success");
        } catch (Exception e) {
            System.out.println("Connection Failed");
        }
    }
    public static Connection getConnection() {
        return con;
    }
}