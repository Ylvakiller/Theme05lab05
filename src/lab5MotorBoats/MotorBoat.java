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
	 * @param capacity The capacity which this MotorBoat object should have
	 */
	public MotorBoat(int capacity){
		this.capacity = capacity;
		maxSpeed = 0;
		currentSpeed = 0;
	}
	
	/**
	 * Constructor that sets the capacity of the fuel tank to the input.
	 * And sets the maxSpeed to the input
	 * @param capacity The capacity which this MotorBoat object should have
	 * @param maxSpeed The maximum speed which this MotorBoat object should have
	 */
	public MotorBoat(int capacity, double maxSpeed){
		this.capacity = capacity;
		this.maxSpeed = maxSpeed;
		currentSpeed = 0;
	}
	
	/**
	 * Will check if the currentSpeed is not larger as the max speed, otherwise will change the currentSpeed to the maximum speed
	 * @param capacity The capacity which this MotorBoat object should have
	 * @param maxSpeed The maximum speed which this MotorBoat object should have
	 * @param currentSpeed The current speed which this MotorBoat object should have
	 */
	public MotorBoat(int capacity, double maxSpeed, double currentSpeed){
		this.capacity = capacity;
		this.maxSpeed = maxSpeed;
		this.currentSpeed = currentSpeed;
		if(!checkSpeed()){
			this.currentSpeed = maxSpeed;
		}
	}
	
	/**
	 * Checks if the motorboat is not going to go faster then his maximum speed
	 * @return a true if the speed is correct
	 */
	private boolean checkSpeed(){
		if (maxSpeed>=currentSpeed){
			return true;
		}else{
		return false;
		}
	}
	
	/**
	 * Will check if the maximum speed of the 2 motorboat objects are the same
	 * @param first The first MototBoat object
	 * @param second The second MotorBoat object
	 * @return true if both MotorBoat object have the same maximum speed
	 */
	public static boolean testMaxSpeed(MotorBoat first, MotorBoat second){
		return false;	
	}
	
	/**
	 * Will compare which motorboat is going faster
	 * Should be just in conjunction with the testMaxSpeed method
	 * @param first The first MotorBoat object
	 * @param second The MotorBoat object to compare to.
	 * @return true if the first motorboat has an higher speed then the second
	 */
	public static boolean getHighestMaxSpeed(MotorBoat first, MotorBoat second){
		return false;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the currentSpeed
	 */
	public double getCurrentSpeed() {
		return currentSpeed;
	}

	/**
	 * @param currentSpeed the currentSpeed to set
	 */
	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	
}
