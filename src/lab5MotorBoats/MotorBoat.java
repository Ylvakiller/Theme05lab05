package lab5MotorBoats;

/*
 * Description as found on bb:
 * MotorBoats: 
 * Consider a class MotorBoat that represents motorboats. A motorboat has attributes for: 
 * o the capacity of the fuel tank 
 * o the maximum speed of the boat 
 * o the current speed of the boat 
 * 
 * Add methods to the class to perform the following tasks: 
 * o Set the capacity of the fuel tank of a MotorBoat object. 
 * o Set the maximum speed of a MotorBoat object. 
 * o Set the current speed of a MotorBoat object.  
 * o Test whether two MotorBoat objects are equal (have the same maximum speed, same current speed and the same fuel tank capacity). 
 * o Test whether two MotorBoat objects have the same maximum speed. 
 * o Test whether two MotorBoat objects have the same fuel tank capacity. 
 * o Test whether one MotorBoat object currently drives faster than another 
 * o Test whether one MotorBoat object can drive faster than another (comparing the maximum speed)
 * Write a driver (test) program that demonstrates each of the test method, with at least one true and one false case for each of the methods tested. 
 */

/**
 * This class will hold the complete assignment from the motorboat assignment
 * @author Ylva
 *
 */
public class MotorBoat {
	
	/**
	 * The capacity of the fuel tank
	 */
	public int capacity;
	
	/**
	 * The maximum speed of this motorboat 
	 */
	public double maxSpeed;
	
	/**
	 * The current speed of this motorboat
	 */
	public double currentSpeed;

	/**
	 * Default constructor, sets all variables to 0
	 * This constructor should only rarely be used
	 */
	public MotorBoat(){
		capacity = 0;
		maxSpeed = 0;
		currentSpeed = 0;
	}
	
	/**
	 * Constructor that sets the capacity of the fuel tank, all other variables are set to 0
	 * @param capacity The capacity which you want this motorboat to have
	 */
	public MotorBoat(int capacity){
		
	}
	
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @return the currentSpeed
	 */
	public double getCurrentSpeed() {
		return currentSpeed;
	}
}
