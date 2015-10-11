import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter {
    
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "STN,DATE,TIME,DEWP,STP,SLP,VISIB,WDSP,TEMP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR";
   
    
        public static void writeCsvFile(String fileName) {

            //random waardes om te testen
           Csv csv1 = new Csv("31-10-10","MULL OF GALLOWAY LH","UNITED KINGDOM","54.633","-4.85","78","65","34","76","78","88","456","45");
           Csv csv2 = new Csv("31-10-10","SOMETHING","NETHERLANDS","54.633","-6.55","345","5676","GFGF","56","GHGH","88","456","45");


           List csvArray = new ArrayList();
           
           //random waardes gaan in array
           csvArray.add(csv1);
           csvArray.add(csv2);


           //creer filewriter
           FileWriter fileWriter = null;
           
        try {
            
            fileWriter = new FileWriter(fileName);
            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);           

            //Write a new student object list to the CSV file             
		
            for (int i = 0; i < csvArray.size(); i++) {
                
                System.out.println(csvArray.get(i));
            }

            System.out.println("CSV file was created successfully !!!");
            
        }catch (Exception ex){
            
                System.out.println("Error in CsvFileWriter !!!");
            
            }finally {
             
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
            }
        }

    }
    
}
