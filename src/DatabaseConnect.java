import java.sql.*;

public class DatabaseConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DBNAME = "unwdmi";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public DatabaseConnect() {
        setConnection();
    }

//    Make connection with the Dtabase
    public void setConnection(){
        try {
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(URL + DBNAME, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("There has been an error. Can't connect to database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

//    Database interaction
    public ResultSet excecuteSQL (String statement) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            return rs;

        } catch (SQLException ie){
            System.out.println("Can't excecute SQL statement");
            ie.printStackTrace();
            return null;

        }
    }

    public void insertSQL(String tablename, String values){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + tablename + " VALUES(" + values + ")");
            stmt.execute();

        } catch (SQLException ie){
            System.out.println("Can't excecute SQL statement (insert)");
            ie.printStackTrace();
        }
    }
}
