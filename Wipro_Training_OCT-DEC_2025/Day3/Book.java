/*
Book Class
Create a Java class named Book to represent a book. The class should have the following features:

Instance Variables:

title (String): to store the title of the book.

author (String): to store the name of the author.

year (int): to store the publication year of the book.



Constructors:

A default constructor that initializes the title, author, and year to default values ("Not specified", "Unknown", and 0, respectively).

A parameterized constructor that takes values for title, author, and year and initializes the corresponding instance variables.



Methods: A method named displayInfo that prints the details of the book (title, author, and year).



Application: In the main method, create two instances of the Book class using both the default constructor and the parameterized constructor.

Call the displayInfo method on each instance to display the details of the books. Your goal is to implement the Book class with appropriate constructors and methods as described.
 */

package Day3;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("Book 1:");
        BookDetail book1 = new BookDetail();
        book1.displayInfo();

        System.out.println("Book 2:");
        BookDetail book2 = new BookDetail("Java Programming", "Sakshi", 2024);
        book2.displayInfo();
    }
}

class BookDetail {
    String title;
    String author;
    int year;

    public BookDetail() {
        this.title = "Not specified";
        this.author = "Unknown";
        this.year = 0;
    }

    public BookDetail(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("Year: " + this.year);
        System.out.println();
    }
}
