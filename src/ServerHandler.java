import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.Map.Entry;

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
        measurementArray = xmlParser.xmlToMeasurementObjects(rawXML);


        // 2. Validate the data
        measurementArray = dataCheck(measurementArray);

        // 3. Add measurement to the MeasurementCollection
        for (Measurement measurement: measurementArray) {
            MeasurementCollection.add(measurement);

            // Send measurement info to Process Speed Profiler
            ProcessingSpeedProfiler.addProcessingCount();
            ProcessingSpeedProfiler.processedCountDirect++;
        }
    }

    /**
     * Check for missing values (null values) and fix them by extrapolating
     */

    public ArrayList<Measurement> dataCheck(ArrayList<Measurement> measurementArray) {
        // Loop through every Measurement object in array
        for (int i = 0; i < measurementArray.size(); i++) {

            // Set measurement object to variable
            Measurement fixedMeasurement = measurementArray.get(i);

            // Loop through every weather item in Measurement Map
            for (Map.Entry<String, String> entry : fixedMeasurement.getMeasurementsMap().entrySet()){

                // If a value is null, we should extrapolate
                if(entry.getValue() == null){
                    

                    // Get history Stack of this particular station and get the average of our missing value.
                    try {
                        Stack<Measurement> stationHistory_temp = MeasurementCollection.stationHistory.get(fixedMeasurement.getStation_id());

                        int totalValue = 0;
                        int averageCount = 0;

                        for(Measurement obj : stationHistory_temp)
                        {
                            averageCount++;
                            totalValue += obj.getMeasurementsMapItem(entry.getKey());
                        }

                        System.out.println("New value: " + totalValue / averageCount);

                        // Set extrapolated value in map and update the Array list
//                        entry.setValue("lel");
//                        System.out.println("Null waarde veranderd AVG: " + averageCount + " | avgInt" + averageInt);
                    } catch (Exception e){

                    }

                    // Update arraylist
                    measurementArray.set(i, fixedMeasurement);
                }
            }
        }
        return measurementArray;
    }
}
