import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHandler implements Runnable {

    private static Socket socket;
    private ArrayList<Measurement> measurementArray;

    public ServerHandler(Socket new_socket) throws IOException {
        socket = new_socket;
    }

    /**
     * Get input from the generator. The generator will send the weather details in a XML file.
     * We need to convert this XML with our XMLParser and then write it to the database.
     */

    @Override
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
                    dataProcessing(buffer.toString());
                    buffer.setLength(0);
                }
            }

            socket.close();

        } catch (IOException exception) {
            System.out.println("IO exception occured!");
            exception.printStackTrace();
        }
    }

    /**
     * Process the XML data in the following order:
     *      1. Parse the XML data and create a Measurement object for each measurement
     *      2. Validate the data by using the DataCheck class
     *      3. Add measurement to the MeasurementCollection
     *      4. Write all measurements to CSV or SQL (WILL BE DONE IN MEASUREMENTCOLLECTION CLASS)
     */

    public void dataProcessing(String rawXML){
        // 1. Parse the XML data and create a Measurement object for each measurement
        XMLParser xmlParser = new XMLParser();
        measurementArray = xmlParser.xmlToMeasurmentObjects(rawXML);

        // 2. Validate the data by using the DataCheck class
//        DataCheck dataCheck = new DataCheck();
//        measurementArray = dataCheck.checkData(measurementArray);

        // 3. Add measurement to the MeasurementCollection
        for (Measurement measurement: measurementArray) {
            MeasurementCollection.add(measurement);
        }

    }
}
