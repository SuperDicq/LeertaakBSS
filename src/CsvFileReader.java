import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {

 //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    
    //Student attributes index
    private static final int STUDENT_ID_IDX = 0;
    private static final int STUDENT_FNAME_IDX = 1;
    private static final int STUDENT_LNAME_IDX = 2;
    private static final int STUDENT_GENDER = 3;
    private static final int STUDENT_AGE = 4;
     
    public static void readCsvFile(String fileName) {

        BufferedReader fileReader = null;
   
        try {


            List csvArray = new ArrayList();

            String line = "";


            fileReader = new BufferedReader(new FileReader(fileName));

            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {

                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);

                if (tokens.length > 0) {

                    //Create a new student object and fill his  data

                    Csv csv = new Csv();

                    csvArray.add(csv);
                }
            }

           

            for (Object print : csvArray) {

                System.out.println(print.toString());

            }

        }

        catch (Exception e) {

            System.out.println("Error in CsvFileReader !!!");

            e.printStackTrace();

        } finally {

            try {

                fileReader.close();

            } catch (IOException e) {

                System.out.println("Error while closing fileReader !!!");

                e.printStackTrace();

            }

        }

 

    }


}
