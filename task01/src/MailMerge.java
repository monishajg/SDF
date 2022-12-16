import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailMerge {
    public static void main(String[] args) throws IOException {

        // Parse command line arguments
        String csvFileName = args[0];
        String templateFileName = args[1];

        // Check that the required arguments are provided
        if (args.length < 2) {
            System.out.println("Usage: java MailMerge csv_file template_file");
            return;
        }

        // Read the CSV file
        BufferedReader sprdshtreader = null;
        List<String> sprdsht = new ArrayList<String>();
        Map<String, List<String>> datacol = new HashMap<>();

        try {
            sprdshtreader = new BufferedReader(new FileReader(args[0]));
        
            String line;
            while ((line = sprdshtreader.readLine()) != null) {
            String[] splitline = line.split(",");

            List<String> fname = new ArrayList<>();
            fname.add(splitline[0]);

            List<String> lname = new ArrayList<>();
            lname.add(splitline[1]);

            List<String> address = new ArrayList<>();
            address.add(splitline[2]);

            List<String> years = new ArrayList<>();
            years.add(splitline[3]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}// MailMerge
}// class
