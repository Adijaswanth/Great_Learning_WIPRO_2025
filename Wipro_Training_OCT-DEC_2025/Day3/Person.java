/*Write a program that takes input from the user and creates an object of a class named 'Person'. 
The 'Person' class should have two member variables: 'name' and 'age'. 
The program should prompt the user to enter their name and age, create a 'Person' object with the entered values, 
and then display the name and age of the person. */

package Day3;

import java.util.Scanner;

public class Person {
    String name;
    int age;

    public static void main(String[] args) {
        Person person=new Person();
        Scanner sc=new Scanner(System.in);
        String a =sc.nextLine();
        int b=sc.nextInt();
        person.name=a;
        person.age=b;

        System.out.println("Name: "+person.name);
        System.out.println("Age: "+person.age);
        sc.close();
    }
}