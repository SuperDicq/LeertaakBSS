import java.sql.Connection;
import java.sql.DriverManager;

public class Main {


    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "unwdmi";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);

            conn.createStatement("SELECT name FROM stations")

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            conn.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
