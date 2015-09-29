/**
 * Created by Thijs on 25/09/2015.
 */

import java.io.*;
import java.net.Socket;
import java.sql.Connection;

public class ServerHandler implements Runnable {

    private static Socket socket;
    private static XMLParser xmlparser;

    public ServerHandler(Socket new_socket) throws IOException {
        socket = new_socket;
        this.xmlparser = xmlparser;

    }

    /**
     * Get input from the generator. The generator will send the weather details in a XML file.
     * We need to convert this XML with our XMLParser and then write it to the database.
     */

    public void run(){
        try {
            // Create inputstream to be able to receive data from the client
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Retrieve XML input of client and pass it along the XML parser
            String line;
            StringBuffer buffer = new StringBuffer();

            while((line = br.readLine()) != null) {
                buffer.append(line).append("\n");

                if(line.contains("</WEATHERDATA>")){
//                    System.out.println("---------------");
                    XMLParser xmlparser = new XMLParser();
                    xmlparser.readXML(buffer.toString());
                    buffer.setLength(0);
                }
            }

            socket.close();

        } catch (IOException exception) {
            System.out.println("IO exception occured!");
            exception.printStackTrace();
        }
    }
}
