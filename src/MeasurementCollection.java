/**
 * Created by Thijs on 11-10-2015.
 */

import java.util.ArrayList;

/**
 * This class will store all measurements
 */

public class MeasurementCollection {

    public static ArrayList<Measurement> measurementCollectionArray;
    private static ArrayList<Measurement> measurementCollectionArray_TEMP;
    private static final int BATCHSIZE = 500;
    private static boolean sql_enabled;
    private static boolean csv_enabled;

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

        if(sql_enabled){
            writeToSQL();
        }

        if(csv_enabled){
            writeToCSV(measurementCollectionArray_TEMP);
        }


        if(measurementCollectionArray.size() >= BATCHSIZE){
            measurementCollectionArray_TEMP = measurementCollectionArray;
            measurementCollectionArray.clear();

            System.out.println("Current collections size: " + measurementCollectionArray.size() +  "/" + BATCHSIZE);

            // Write to sources
            if(sql_enabled){
                writeToSQL();
            }

            if(csv_enabled){
                writeToCSV(measurementCollectionArray_TEMP);
            }

        }
    }

    /**
     * Write all measurements in the Collections array to a external source
     */

    private static void writeToCSV(ArrayList<Measurement> measurementBatch){
        CsvFileWriter csv = new CsvFileWriter();
        csv.writeCsvFile("weatherdata.csv", measurementBatch);
    }

    private static void writeToSQL(){
        // CODE HIER
        System.out.println("SQL schrijven!");
    }
}
