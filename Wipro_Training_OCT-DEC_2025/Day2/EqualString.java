/*
You are given a string s. In one move you can change any character to another character.
You have to make a string which consists of the same character. Find the minimum move to do this task. 
*/
package Day2;

import java.util.Scanner;

public class EqualString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        
        int[] freq = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        int maxFreq = 0;
        for (int count : freq) {
            maxFreq = Math.max(maxFreq, count);
        }
        
        int minMoves = s.length() - maxFreq;
        System.out.println(minMoves);
        
        sc.close();
    }
}