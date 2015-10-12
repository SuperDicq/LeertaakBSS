/**
 * Created by Thijs on 11-10-2015.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will store all measurements
 */

public class MeasurementCollection {

    public static ArrayList<Measurement> measurementCollectionArray;
    private static ArrayList<Measurement> measurementCollectionArray_TEMP;
    private static final int BATCHSIZE = 1500;
    private static boolean sql_enabled;
    private static boolean csv_enabled;
    private static boolean csv_initiliazed = false;

    public MeasurementCollection(boolean sql, boolean csv) {
        measurementCollectionArray = new ArrayList<Measurement>();
        sql_enabled = sql;
        csv_enabled = csv;

    }

    public static ArrayList<Measurement> getMeasurementCollectionArray(){
        return getMeasurementCollectionArray();
    }

    /**
     * Add measurements to the collection. Once there are more than BATCHSIZE measurements stored, we will
     * write the data to a source.
     */

    public synchronized static void add(Measurement measurement){
        measurementCollectionArray.add(measurement);

        if(measurementCollectionArray.size() >= BATCHSIZE){
            measurementCollectionArray_TEMP = measurementCollectionArray;
//            measurementCollectionArray.clear();
            System.out.println("Current collections size: " + measurementCollectionArray.size() + "/" + BATCHSIZE);

            // Write to sources
            if(sql_enabled){
                writeToSQL();
            }

            if(csv_enabled){
                writeToCSV(measurementCollectionArray_TEMP, "WeatherData.csv");
            }
        }
    }

    /**
     * ----------------------------------------------------------------------------------
     * WRITE TO CSV FILE
     * ----------------------------------------------------------------------------------
     */

    private static StringBuilder csvText = new StringBuilder();

    private static void writeToCSV(ArrayList<Measurement> measurementBatch, String fileName){

        // We need to put this data in front of csv string (only called once)
        if(!csv_initiliazed){
            csvText.append("sep=,\nstation_id,local_date,local_time,temperatuur,dauwpunt,luchtdruk_station,luchtdruk_zee,zichtbaarheid,neerslag,sneeuwdiepte,bewolking,windrichting,windsnelheid,gebeurtenissen\n");
            csv_initiliazed = true;
        }

        // Get CSV data from every measurement
        for (Measurement measurement: measurementBatch) {
            csvText.append(measurement.getCSV()).append("\n");
        }

        // Create fileWriter
        FileWriter fileWriter = null;

        // Write data to csv file
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(String.valueOf(csvText.toString()));
            System.out.println("CSV file was created successfully");

        }catch (Exception ex){

            System.out.println("Error in CsvFileWriter");

        }finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter");
            }
        }

        // Clear measurement history
        measurementCollectionArray.clear();
    }

    /**
     * ----------------------------------------------------------------------------------
     * WRITE TO SQL DATABASE
     * ----------------------------------------------------------------------------------
     */

    private static void writeToSQL(){
        // CODE HIER
        System.out.println("SQL schrijven!");
    }
}
