import java.sql.*;

public class DatabaseConnect {
//
    private static final String URL = "jdbc:mysql://topster21.net:13462/";
    private static final String DBNAME = "projectdb2";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "anotheruser";
    private static final String PASSWORD = "3Pl8DnOXcXj7khx3bEcG";

//    private static final String URL = "jdbc:mysql://localhost:3306/";
//    private static final String DBNAME = "unwdmi";
//    private static final String DRIVER = "com.mysql.jdbc.Driver";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "";

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

//        insertSQL("INSERT INTO timezone VALUES(1337, 12, 41);");
//        excecuteSQL("SELECT name FROM stations");
    }

    public Connection getConnection(){
        return connection;
    }

//    Database interaction
    public ResultSet executeSQL (String statement) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(statement);


            while (rs.next()) {
                String columnValue = rs.getString("name");
                System.out.println(columnValue);
            }

            return rs;

        } catch (SQLException ie){
            System.out.println("Can't excecute SQL statement");
            ie.printStackTrace();
            return null;

        }
    }

    public void insertSQL(String statement){
        try {
            Statement stmt = connection.createStatement();
            String sql = statement;
            stmt.executeUpdate(sql);
            System.out.println("inserting");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
