package myapp;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome.\n");

        while (true) {
            System.out.print(">");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bye bye");
                break;
            }

            String[] parts = input.split(" ");

            if (parts.length != 3) {
                System.out.println("Invalid input");
                continue;
            }

            double operand1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double operand2 = Double.parseDouble(parts[2]);
            double result = 0;
            double last = 0f;

            if (parts[0].contains("$last") && parts[2].contains("$last")){ 
                operand1 = last; 
                operand2 = last; 
              } else if (parts[0].contains("$last")) { 
                operand1 = last;
                operand2 = Float.parseFloat(parts[2]); 
              } else if (parts[2].contains("$last")) { 
                operand2 = last; 
                operand1 = Float.parseFloat(parts[0]); 
              } else { 
                operand1 = Float.parseFloat(parts[0]); 
                operand2 = Float.parseFloat(parts[2]); 
              } 

              switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                default:
                    System.out.println("Invalid operator");
                    continue;
                }
                
            last = result;
            System.out.println(result);

        }//while loop
    }//class
}//main