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
		
	}

}
