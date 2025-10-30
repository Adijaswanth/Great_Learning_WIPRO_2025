/*Sum of All Numbers
Find the sum of the digits using recursion */
package Day3;

import java.util.Scanner;

public class Main {

    public static int sumOfDigits(int n) {
        if (n == 0) {
            return 0;
        }
        return (n % 10) + sumOfDigits(n / 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int result = sumOfDigits(number);
        System.out.println(result);
        scanner.close();
    }
}