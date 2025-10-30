/*Write a program in java to check whether the given number is an even number or not using if else statement. */

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int number=scanner.nextInt();
        if(number%2==0){
            System.out.println("No is Even");
        }
        else{
            System.out.println("No is odd");
        }
        scanner.close();
    }
}