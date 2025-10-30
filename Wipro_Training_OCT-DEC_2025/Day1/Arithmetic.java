/*Write a program that takes two numbers as input from the user and performs arithmetic operations on them using the 
arithmetic operators (sum, difference, product, quotient, remainder) in Java.

 */

 import java.util.Scanner;

public class Arithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        int sum = num1 + num2;
        int difference = num1 - num2;
        int product = num1 * num2;

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);

        if (num2 == 0) {
            System.out.println("Quotient: Error: Cannot divide by zero");
            System.out.println("Remainder: Error: Cannot divide by zero");
        } else {
            int quotient = num1 / num2;
            int remainder = num1 % num2;
            System.out.println("Quotient: " + quotient);
            System.out.println("Remainder: " + remainder);
        }
        scanner.close();
    }
}
