/**
 * Created by Thijs on 11-10-2015.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Arrays.asList;

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
    
    public static ArrayList<String> getBatchAverages() {
    	
    	// Set averages to 0 if measurementColletionArray is empty. Better solution?
    	ArrayList<String> averageMeasurement = (ArrayList<String>) asList("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
    	// ArrayList that is not Strings for calculations
    	ArrayList<Integer> averageMeasurementInt = stringsToIntegers(averageMeasurement);
    	
    	// Iterate through batch
    	for(int i = 0; i < measurementCollectionArray.size(); i++) {
    			
    		// Get Measurement object of current iteration
    		Measurement measurementObject = measurementCollectionArray.get(i);
    		
    		// Get a map of the Measurement object so we can iterate
    		Map<String, String> measurementMap = measurementObject.getMeasurementsMap();
    			
    		int j = 0;
    		Iterator<Entry<String, String>> it = measurementMap.entrySet().iterator();
    		
    		// Iterate through map
    		while(it.hasNext()) {
    				
    			Map.Entry<String, String> pair = (Entry<String, String>)it.next();
    			
    			// Create a total value for every measurement to divide later
    			Integer addValue = Integer.parseInt(pair.getValue());
    			Integer currentValue = averageMeasurementInt.get(j);
    			currentValue = currentValue + addValue;
    			
    			// Store total value in averageMeasurementInt for now
    			averageMeasurementInt.set(j, currentValue);
    			
    			j += 1;
    			
    		}
    		
    	}
    	
    	// Amount of total measurements not set for scaling
		int totalMeasurements = measurementCollectionArray.size();
    	
		// Divide total value by batch size for every measurement
    	for(int i = 0; i < averageMeasurementInt.size(); i++) {
 
    		Integer average = averageMeasurementInt.get(i);	
    		average = average / totalMeasurements;
    		averageMeasurementInt.set(i, average);
    	
    	}
    	
    	// And finally convert back to strings
    	averageMeasurement = integersToStrings(averageMeasurementInt);
    	 
		return averageMeasurement;
    			
    }
   
    // Convert an ArrayList of Strings to an Arraylist of Integers
    private static ArrayList<Integer> stringsToIntegers(ArrayList<String> measurements){
    	
    	ArrayList<Integer> integers = new ArrayList<Integer>();
    	
    	for(String value: measurements) {
    		
    		integers.add(Integer.parseInt(value));
    			
    	}
    	
    	return integers;
    	
    }
    
    // Convert an ArrayList of Integers to an ArrayList of Strings
    private static ArrayList<String> integersToStrings(ArrayList<Integer> measurements){
    	
    	ArrayList<String> strings = new ArrayList<String>();
    	
    	for(Integer value: measurements) {
    		
    		strings.add(String.valueOf(value));
    			
    	}
    	
    	return strings; 
    	
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
