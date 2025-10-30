/*
Constructor overloading
Create one default constructor to initialize the fields as default values to calculate volume of the cube and one parameterized constructor to pass parameters and initialize them accordingly. You can pass 3 integer parameters at runtime 
(While using parameterized constructor, the program, should also display the output with the default constructor also)
 */
package Day3;

import java.util.Scanner;

public class ConstructirOverloading {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Constructor without parameter");
        Constructor cuboid1 = new Constructor();
        cuboid1.displayVolume();

        int l = scan.nextInt();
        int w = scan.nextInt();
        int h = scan.nextInt();
        
        System.out.println("Constructor with parameter");
        Constructor cuboid2 = new Constructor(l, w, h);
        cuboid2.displayVolume();

        scan.close();
    }
}

class Constructor {
    double length;
    double width;
    double height;

    public Constructor() {
        this.length = 100.0;
        this.width = 10.0;
        this.height = 1.0;
    }

    public Constructor(int l, int w, int h) {
        this.length = l;
        this.width = w;
        this.height = h;
    }

    public double getVolume() {
        return this.length * this.width * this.height;
    }

    public void displayVolume() {
        System.out.println("Volume is " + getVolume());
    }
}
