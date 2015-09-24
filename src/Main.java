import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static DatabaseConnect database;
    private static ResultSet sqloutput;

    public static void main(String[] args) throws SQLException {
        database = new DatabaseConnect();

//        Test statement
//        sqloutput = database.excecuteSQL("SELECT name FROM stations WHERE country = 'AFGHANISTAN';");
//        while (sqloutput.next()) {
//            System.out.println(sqloutput.getString("name"));
//        }

    }
}
