/*Write a function to reverse each word in a string.
Description :- A method has to be created that takes a string as input and extracts each word 
from that string and then reverse each word individually and gives the output as a reversed string.  */
package Day2;

import java.util.Scanner;

public class Reverse{
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        StringBuilder outputBuilder = new StringBuilder();
        
        while (sc.hasNext()) {
            String word = sc.next();
            
            StringBuilder wordToReverse = new StringBuilder(word);
            wordToReverse.reverse();
            
            outputBuilder.append(wordToReverse);
            outputBuilder.append(" ");
        }
        
        System.out.println(outputBuilder.toString().trim());
        
        sc.close();
    }
}