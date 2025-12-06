/*
You are required to catch Arithmetic or number format exceptions if present using multiple catch blocks in the code 
for finding of 99 with the number entered by user, else print "n is a factor of 99" or "n is not a factor of 99".
 */
package Day5;

import java.util.Scanner;
public class Test2 {

    public static void main(String[] args) {
 Scanner scn = new Scanner(System.in);
        //write your answer here
        try {
            String input = scn.nextLine();
            int num = Integer.parseInt(input);

            if (99 % num == 0) {
                System.out.println(num + " is a factor of 99");
            }else{
                System.out.println(num + " is a not a factor of 99");
            }
        }
        catch (ArithmeticException e ) {
            System.out.println("Arithemetic Exception " + e);
        }
        catch (NumberFormatException e) {
            System.out.println("Number Format Exception " + e);
        }
    }
}
