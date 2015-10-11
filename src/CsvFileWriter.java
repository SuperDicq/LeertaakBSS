import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "station_id,local_date,local_time,temperatuur,dauwpunt,luchtdruk_station,luchtdruk_zee,zichtbaarheid,neerslag,sneeuwdiepte,bewolking,windrichting,windsnelheid,gebeurtenissen";

    public static void writeCsvFile(String fileName, ArrayList<Measurement> measurementBatch) {

        // Create fileWriter
        FileWriter fileWriter = null;

        try {

            fileWriter = new FileWriter(fileName, true);

            // Tell Excel the comma is a delimiter
            fileWriter.append("sep=,");
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

//            Write a new student object list to the CSV file
            for (Measurement measurement: measurementBatch) {
                fileWriter.append(String.valueOf(measurement.getStation_id()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getLocal_date()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getLocal_time()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getTemperatuur()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getDauwpunt()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getLuchtdruk_station()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getLuchtdruk_zee()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getZichtbaarheid()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getNeerslag()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getSneeuwdiepte()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getBewolking()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getWindrichting()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getWindsnelheid()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(measurement.getGebeurtenissen()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

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

    }

}
