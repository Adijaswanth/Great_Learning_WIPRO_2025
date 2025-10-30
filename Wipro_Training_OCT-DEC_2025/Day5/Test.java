/*
Write a Java program that takes two integers as input and performs division. 
The program should handle exceptions for invalid input (non-integer input) and division by zero. 
The program should continue to prompt the user for valid input until successful division is performed.
 */

package Day5;
import java.util.InputMismatchException;
import java.util.*;

class Test {
    public static void main(String args[] ) throws Exception {
        
        // Write your code here
        Scanner sc = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            try {
                //System.out.print("Enter the first Number: ");
                int n1 = sc.nextInt();

                //System.out.print("Enter the Second Number: ");
                int n2 = sc.nextInt();

                int re = n1 / n2;
                System.out.println("Result: " + re);
                valid = true; 
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
            catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero. Please enter a valid divisor.");
            }
        }
        sc.close();
    }
}  

