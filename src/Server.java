import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static DatabaseConnect database;
    private static final boolean sql_enabled = false;
    private static final boolean csv_enabled = true;

    /**
     * Make a new server socket and wait for clients to connect. Once a client connects with the server, a new instance of ServerHandler will be created.
     */

    public static void main(String args[]) throws IOException{

        // Make Measurement Collection, which stores all measurements
        new MeasurementCollection(sql_enabled, csv_enabled);

        // Listen to server port (7789 is default port of Unwdmi generator)
        int portNumber = 7789;
        database = new DatabaseConnect();

        ServerSocket serverSocket = new ServerSocket(portNumber);

        // Await connections of clients
        try {

            Socket socket;
            System.out.println("Server started on port " + portNumber);

            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        
        }finally{
        	serverSocket.close();
        }
    }

}