package lab5MotorBoats;
/**
 * Will test all the features of the motorboat class
 * @author Ylva
 *
 */
public class MotorBoatTester {
	/**
	 * Well what will a main method do... anyone any guess?
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("I will test the motorBoat class for you.");
		System.out.println("I will first create a MotorBoat with the following properties:");
		System.out.println("The capacity will be 50 liters.");
		System.out.println("The maximum speed will be 40 kmph");
		System.out.println("It will be going 20 kmph");
		MotorBoat first = new MotorBoat(50,40,20);
		System.out.println("Okay thats done, now let us see what the MotoBoat has:");
		System.out.println("The capacity is: " + first.getCapacity() + " liters");
		System.out.println("The maximum speed is: " + first.getMaxSpeed() + " kmph");
		System.out.println("The MotorBoat is going: " + first.getCurrentSpeed() + " kmph");
		System.out.println();
		System.out.println("Okay so that works.");
		System.out.println("Now let us see if we can increase the speed past the maximum speed by adding 30 to the speed:");
		if (first.setCurrentSpeed(first.getCurrentSpeed()+30)){
			System.err.println("Hmmm we should not be able to get here..");
		}else{
			System.out.println("Okay we cannot increase the speed past the maximum speed.");
		}
		System.out.println("But does the MotorBoat store the correct value after attempting this?");
		System.out.println("(the speed should be 20 if this works)");
		System.out.println("The MotorBoat is going: " + first.getCurrentSpeed() + " kmph");
		System.out.println("So it keeps the correct speed, thats good.");
		System.out.println();
		System.out.println("Okay so we cannot increase the speed past the maximum speed, but can we increase the speed at all?");
		System.out.println("Lets try increasing the speed by 10.");
		if (first.setCurrentSpeed(first.getCurrentSpeed()+10)){
			System.out.println("Looks like it works, lets test it.");
		}else{
			System.err.println("Hmmm so it did not work... something is wrong");
		}
		System.out.println("The MotorBoat is going: " + first.getCurrentSpeed() + " kmph");
		System.out.println("Okay so we can in fact change the speed correctely if the new speed is not larger as the maximum speed");
		System.out.println();
		System.out.println("But what if we lower the maximum speed to be below the current speed?");
		System.out.println("Currentely we have:");
		System.out.println("The maximum speed is: " + first.getMaxSpeed() + " kmph");
		System.out.println("The MotorBoat is going: " + first.getCurrentSpeed() + " kmph");
		System.out.println("So if we where to lower the maximum speed to 20 kmph what happens?");
		first.setMaxSpeed(20);
		System.out.println("Now we get:");
		System.out.println("The maximum speed is: " + first.getMaxSpeed() + " kmph");
		System.out.println("The MotorBoat is going: " + first.getCurrentSpeed() + " kmph");
		
		System.out.println("So if we lower the maximum speed below the current speed it will simply max out the boat, but stay in a valid speed.");
		
		System.out.println();
		System.out.println("Now on to something else.");
		System.out.println("If we where to create a new motorboat, and we would not specify anything, what then would happen?");
		MotorBoat second  =  new MotorBoat();
		
		System.out.println("Curren values:");
		System.out.println("The capacity is: " + second.getCapacity() + " liters");
		System.out.println("The maximum speed is: " + second.getMaxSpeed() + " kmph");
		System.out.println("The MotorBoat is going: " + second.getCurrentSpeed() + " kmph");
		
		System.out.println("Hmmm everything is 0... lets try to set a better capacity in this boat, 20 liters will do.");
		second.setCapacity(20);
		System.out.println("Now we have " + second.getCapacity() + " liters as our capacity.");
		System.out.println("We can now also check if the capacities of these 2 boats are the same.");
		System.out.println("Our first boat has a capacity of 50, and our second boat has a capacity of 20.");
		System.out.println("We know that these are not the same, but does the program know?");
		if (MotorBoat.testCapacity(first, second)){
			System.err.println("Hmmm it thinks they are the same, that is incorrect");
		}else{
			System.out.println("It says that the capacities are not the same, that is correct.");
		}
		
		System.out.println();
		System.out.println("But what if we would make the capacities the same, what would then happen?");
		System.out.println("I'll set the capacity of the second boat to the value of the first boat and check again.");
		second.setCapacity(first.getCapacity());
		
		if (MotorBoat.testCapacity(first, second)){
			System.out.println("Looks like they are the same, nice.");
		}else{
			System.err.println("What they are not the same? Now that is incorrect!");
		}
		
		
	}

}
