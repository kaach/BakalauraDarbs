import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Katrina
 * Date: 14.6.6
 * Time: 19:45
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    static String user = "root";
    static String pass = "zup1nja*";
    static String IP = "192.168.0.105";
    public Main(){}
    public static void main(String[] args) {
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }
        Connection conn = null;
        try {
            String url = "jdbc:mysql://"+IP+":3306/Points";
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StartGUI startGUI = new StartGUI(conn);
        startGUI.setVisible(true);
    }
}