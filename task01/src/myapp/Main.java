package myapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main { //need list and map
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // 1. Parse command line arguments
        String csvFileName = args[0];
        String templateFileName = args[1];

        // 2. Check that the required arguments are provided
        if (args.length < 2) {
            System.out.println("Invoke JVM followed by <CSV file> <template file>");
            return;
        }

        Map<String, List<String>> dataMap = new HashMap<>();
        String[] variableNames = null;

        // 3 Data Source (CSV) File Format
        // 3.1 Read the CSV file & store variables in Map
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvFileName));

            String row;
            int rowNumber = 0;
            while ((row = csvReader.readLine()) != null) {
                if (rowNumber == 0) { // 3.1.1 Header contains the variable names
                    variableNames = row.split(",");
                    
                    variableNames = Arrays.copyOf(variableNames, variableNames.length + 1); // 2nd CSV contains salutation
                    variableNames[variableNames.length - 1] = "salutation";
                    
                    for (String variableName : variableNames) {
                        dataMap.put(variableName, new ArrayList<>());
                    } else {    // 3.1.2 Rows after contain the data
                        String[] data = row.split(",");
                        for (int i = 0; i < variableNames.length; i++) {
                            String[] variables = data[i].split("\n");
                        }
                        for (String variable : variables) {
                            dataMap.get(variableNames[i]).add(variable);
                        }
                    }
                }
            rowNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4 Template File Format
        // 4.1 Read the template and perform the mail merge
        for (int i = 0; i < dataMap.get("salutation").size(); i++) {
            try {
                Scanner templateScanner = new Scanner(new FileReader(templateFileName));
                StringBuilder letterBuilder = new StringBuilder();
                
                while (templateScanner.hasNextLine()) {
                    String line = templateScanner.nextLine();
                    
                    Pattern pattern = Pattern.compile("<<(.+?)>>");
                    Matcher matcher = pattern.matcher(line);
                    
                    while (matcher.find()) {
                        String key = matcher.group(1);
                        List<String> values = dataMap.get(key);
                        
                        if (values != null) {
                            line = line.replace("<<" + key + ">>", values.get(i));
                        }
                    }
                    letterBuilder.append(line).append("\n");
                }
                
                // 4.2 Split the templates
                String[] letters = letterBuilder.toString().split("\n\n");
                
                // 4.3 Print out filled-in templates
                for (String letter : letters) {
                    System.out.println(letter);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }//main
}//class