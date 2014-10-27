Theme05lab05
============
This README is used solely to explain the algorithm used in the AI.

This algorithm is documented in the AI class howEver for clarity I would like to give a final detailed explanation of what happens where:

The AI algorithm consists of a series of steps, if a certain step can be taken the AI will do that, if it can not be taken it will go on to the next step.
There is a step which is not numbered, this is the UltraHardCore step and fixes the one way in which the user can win.

Let me speak a bit about this algorithm before explaining why the UltraHardCore step is in there.

The program will first check if it can win with just one move, and will do so if possible.

Then the program will check if the opposing player can win with his next move, and will try to block that move.

Then the program will attempt to create a fork.

  A fork is a postion in which there are 2 places for the computer to win
  e.g. if the computer is X:
  
  	X--
  	
  	-O-
  	
	--X
 
 
 In this case there is a possibility to fork by doing the following move:
 
	X-X
	
	-O-
	
	--X
	
 Now the program will attempt to create such forks which in turn guarantee a win for the computer.
After this the program will check if it needs to do the ultraHardCore steps or not.
This step is a special way in which to block the specific fork above (including all rotations).
Now if the program was O in the example above then placing an O on the place where X was placed on the second part of the example would not stop the opponent from forking.

This because the opponent could fork by placing an X in the bottom left corner.
The way that the ultraHardCore mode works is that instead of trying to block the opponents fork it will force him into defending.

  e.g.:
  Situation before computer turn:
  
	X--
	
	-O-
	
	--X
 
 Situation after ultraHardCoreTurn:
 
  	XO-
  	
  	-O-
  	
  	--X
 
This will force the opponent to block and thus will effectively stop him from forking.
Now if ultraHardCore is not on or the specific situation is not applicable the computer will simply calculate where the player would place his sign in order to fork and place the computer sign at that position.

Now if all these steps are not taken (which usually means that the game is in the first 1 or 2 moves or so) then the following steps will be checked, the first one of these will be taken.

If the centre block is open, take it.

If the opponent is in a corner, take the opposing corner.

Try to take any unoccupied corner.

Try to take a centre edge space.


Doing the math there are only 138 end board places.

This taking into account rotations and reflections.

For example, the very first move taken, can only be done on 3 places (not 9):

A corner place

The middle of an edge

The centre place


Now using basic algebra we can see that 91 of these are won by the player that starts,
44 are won by the player that did not start
and only 3 positions are unique and cause a draw

Now the game will always try to win or force a draw.

This strategy is well documented however the code used was created by myself.

If you want to know more about this AI there is an excellent paper on this:
http://csjarchive.cogsci.rpi.edu/1993v17/i04/p0531p0561/MAIN.PDF

This paper is what I used to create the AI, and the algorithm can be found in a similair documentation on page number 536 (actual page on the document that I have send you is page 6)

I hope this explanation gives a clear understanding about the AI that I created

Theme05Lab05
