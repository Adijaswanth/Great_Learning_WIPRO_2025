/*
Create a superClass Vehicle that has a method noOfWheels() of return type void and accepts no parameters. 
Create two subclasses Scooter and Car which has method noOfWheels()  

noOfWheels() of Vehicle should display “No of wheels undefined”.

noOfWheels() of Scooter should display “No of wheels 2”.

noOfWheels() of Car should display “No of wheels 4”.
 */
package Day4;

public class Superclass {

    public static void main(String[] args) {
        Vehicle myvehicle=new Vehicle();
        myvehicle.noOfWheels();

        Scooter myscooter=new Scooter();
        myscooter.noOfWheels();

        Car mycar=new Car();
        mycar.noOfWheels();
    }
}
class Vehicle{
    public void noOfWheels(){
        System.out.println("No of wheels undefined");
    }
}
class Scooter extends Vehicle{
    @Override
    public void noOfWheels(){
        System.out.println("No of wheels 2");
    }
}

class Car extends Vehicle{
    @Override
    public void noOfWheels(){
        System.out.println("No of wheels 4");
    }
}