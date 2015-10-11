/**
 * Created by Thijs on 11-10-2015.
 */

import java.util.ArrayList;

/**
 * This class will store all measurements
 */

public class MeasurementCollection {

    public static ArrayList<Measurement> measurementCollectionArray;
    private static final int BATCHSIZE = 0;
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

    public static void add(Measurement measurement){
        measurementCollectionArray.add(measurement);

        if(measurementCollectionArray.size() >= BATCHSIZE){
            System.out.println("Current collections size: " + measurementCollectionArray.size() +  "/" + BATCHSIZE);

            // Write to sources
            if(sql_enabled){
                writeToSQL();
            }

            if(csv_enabled){
                writeToCSV();
            }
        }
    }

    /**
     * Write all measurements in the Collections array to a external source
     */

    private static void writeToCSV(){
        // CODE HIER
        System.out.println("CSV schrijven!");
    }

    private static void writeToSQL(){
        // CODE HIER
        System.out.println("SQL schrijven!");
    }
}
