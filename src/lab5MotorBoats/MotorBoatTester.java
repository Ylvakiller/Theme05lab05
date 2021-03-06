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
		System.out.println();
		System.out.println("Now for testing I will set the value of the maximum speed and the value of the currentspeed to be the same for both boats:");
		second.setMaxSpeed(first.getMaxSpeed());
		second.setCurrentSpeed(first.getCurrentSpeed());
		
		System.out.println("Now the boats have the same values on all fields.");
		System.out.println("Lets start by comparing the top speeds");
		
		if (MotorBoat.testMaxSpeed(first, second)){
			System.out.println("The maximum speeds are the same");
		}else{
			System.err.println("What they are not the same? Now that is incorrect!");
		}
		if (MotorBoat.testCurrentSpeed(first, second)){
			System.out.println("The current speeds are the same");
		}else{
			System.err.println("What they are not the same? Now that is incorrect!");
		}
		
		System.out.println();
		System.out.println("Since we know that all the fields are the same, both boats should be equal to one another right?");
		if (MotorBoat.equals(first, second)){
			System.out.println("And as it turns out the program agrees with that");
		}else{
			System.err.println("Hmmm they are not the same? That is not right...");
		}
		
		System.out.println();
		System.out.println("Now the program can tell which boat can go faster then the other.");
		System.out.println("Since we know that the values are the same we can check what we can get:");
		if (MotorBoat.compareMaxSpeed(first, second)){
			System.err.println("Hmmm the first boat can apparentely go faster then the second... wierd.");
		}else{
			if (MotorBoat.compareMaxSpeed(second, first)){
				System.err.println("Wait, the second boat can go faster? This is not right");
			}else{
				System.out.println("Neither can go faster as the other, this is what we want.");
			}
		}
		
		System.out.println("We can do the same for the current speed...");
		if (MotorBoat.compareCurrentSpeed(first, second)){
			System.err.println("Hmmm the first boat is apparentely going faster then the second... wierd.");
		}else{
			if (MotorBoat.compareCurrentSpeed(second, first)){
				System.err.println("Wait, the second boat is going faster? This is not right");
			}else{
				System.out.println("Neither is going faster as the other, this is what we want.");
			}
		}
		
		System.out.println();
		System.out.println("Now if we where to lower the top speed of the second boar then the speed it is going should also decrease, lets check that");
		System.out.println("Before");
		System.out.println("The maximum speed is: " + second.getMaxSpeed() + " kmph");
		System.out.println("The MotorBoat is going: " + second.getCurrentSpeed() + " kmph");
		second.setMaxSpeed(second.getMaxSpeed()-5);
		System.out.println("After");
		System.out.println("The maximum speed is: " + second.getMaxSpeed() + " kmph");
		System.out.println("The MotorBoat is going: " + second.getCurrentSpeed() + " kmph");
		
		System.out.println("Sweet that works aswell, things are going great!");
		System.out.println();
		System.out.println("But now the boats are not equal right? lets check that.");
		if (MotorBoat.equals(first, second)){
			System.err.println("Hey it thinks they are equal? that is not correct");
		}else{
			System.out.println("Jup they are different, It is looking good for this program.");
		}
		
		System.out.println();
		System.out.println("But since the top speed is now different between the boats we can compare those and find out which one can go faster");
		if (MotorBoat.compareMaxSpeed(first, second)){
			System.out.println("Okay so the first one can go faster then the second one... seems about right");
		}else{
			if (MotorBoat.compareMaxSpeed(second, first)){
				System.err.println("Wait, the second boat can go faster? This is not right");
			}else{
				System.err.println("They both can go the same speed, that is not correct!!");
			}
		}
		
		System.out.println("Okay so that works, what about the currentSpeed?");
		if (MotorBoat.compareCurrentSpeed(first, second)){
			System.out.println("The first one is going faster? jup that is correct");
		}else{
			if (MotorBoat.compareCurrentSpeed(second, first)){
				System.err.println("Wait, the second boat is going faster? This is not right");
			}else{
				System.err.println("They both are going the same speed, that is not correct!!");
			}
		}
		System.out.println("");
		System.out.println("I think we now have tested nearly all the different cases.");
		System.out.println("But for completion I will set the current speed of the first motorboat to 5 kmph");
		System.out.println("This means that the first motorboat can go faster, but is not currentely going faster");
		System.out.println("Now we can run the same checks as just before:");
		System.out.println();
		
		first.setCurrentSpeed(5);
		System.out.println("Comparing top speed");
		if (MotorBoat.compareMaxSpeed(first, second)){
			System.out.println("Okay so the first one can go faster then the second one... seems about right");
		}else{
			if (MotorBoat.compareMaxSpeed(second, first)){
				System.err.println("Wait, the second boat can go faster? This is not right");
			}else{
				System.err.println("They both can go the same speed, that is not correct!!");
			}
		}
		
		System.out.println("And comparing the current speed");
		if (MotorBoat.compareCurrentSpeed(first, second)){
			System.err.println("The first one is going faster? Hey that is not right!");
		}else{
			if (MotorBoat.compareCurrentSpeed(second, first)){
				System.out.println("So the second boat is going faster, sweeet");
			}else{
				System.err.println("They both are going the same speed, that is not correct!!");
			}
		}
		
		System.out.println();
		System.out.println("I think I have checked all the cases now.");
		System.out.println("And based on the results of these cases I would say that the MotorBoat class is working perfectely");
		
	}
}
