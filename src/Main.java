import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static DatabaseConnect database;
    public static XMLParser xmlparser;
    private static ResultSet sqloutput;

    public static void main(String[] args) throws SQLException {
        database = new DatabaseConnect();
        xmlparser = new XMLParser();


        System.out.println(xmlparser.readXML("src/output.xml"));


//        Test statement
//        sqloutput = database.excecuteSQL("SELECT name FROM stations WHERE country = 'AFGHANISTAN';");
//        while (sqloutput.next()) {
//            System.out.println(sqloutput.getString("name"));
//        }
//
//        database.insertSQL("timezone", "20, 4, 5");

    }
}
