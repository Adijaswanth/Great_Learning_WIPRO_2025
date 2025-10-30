package Day4;

import java.util.Scanner;
import java.util.*;
class Cycle {
    // the Bicycle class has two fields
    public int gear;
    public int speed;
 
    // the Bicycle class has one constructor
    public Cycle(int gear, int speed)
    {
        this.gear=gear;
        this.speed=speed;
        //code
    }
 
    // the Bicycle class has three methods
    public void applyBrake(int decrement)
    {
        speed=speed-decrement;
       //code
    }
 
    public void speedUp(int increment)
    {
        speed=speed+increment;
       //code
    }
 
    // toString() method to print info of Bicycle
    public String toString()
    {
        return ("No of gears are " + gear + "\n"
                + "speed of bicycle is " + speed);
    }
}
 
// derived class
class MountainBike extends Cycle {
 
    // the MountainBike subclass adds one more field
    public int seatHeight;
 
    // the MountainBike subclass has one constructor
    public MountainBike(int gear, int speed,
                        int startHeight)
    {
        super(gear,speed);
        this.seatHeight=startHeight;
        // invoking base-class(Bicycle) constructor
       //code
    }
 
    // the MountainBike subclass adds one more method
    public void setHeight(int newValue)
    {
        seatHeight = newValue;
    }
 
    // overriding toString() method
    // of Bicycle to print more info
    @Override public String toString()
    {
        return (super.toString() + "\nseat height is "
                + seatHeight);
    }
}
 
// driver class
/**
 * driver class
 * Renamed to 'Main' as this is likely what the platform expects.
 */
public class Main {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int x= sc.nextInt();
        int y= sc.nextInt();
        int z= sc.nextInt();
        MountainBike mb = new MountainBike(x,y,z);
        System.out.println(mb.toString());
        
        sc.close();
    }
}