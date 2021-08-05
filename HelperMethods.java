/**
* Program Name: HelperMethods.java
* Purpose: Helper methods for the epidemic simulation program
* Coder: Katherine Argall, Roland Turner, and Scott Catton
* Date: Jul. 29, 2021
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class HelperMethods
{
//CLASS WIDE SCOPE AREA
	private final int WIDTH = 700, HEIGHT = 500;
	private final int LAG_TIME = 1; 
	private Timer time;
	public int popSize;

	//Name: Calc Position
	//Description: Calculates the new position of a person object. Created by Bill Pulling
	//Inputs: Person
	//Outputs: none
	public void calcPosition(People person)
	{
		if(person.xFlag == true)
		{
			person.xCoord++;//we have not hit the right side yet so increment xCoord
		}
		else
		{
			//if this is true, we're heading left
			person.xCoord--;
		}
		
		if(person.xCoord == WIDTH - person.diameter)
		{
			//if true, we've hit right edge, flip the flag
			person.xFlag = false;
		}
		if(person.xCoord == 0)
		{
			//if true, we're at left edge, flip the flag
			person.xFlag = true;
		}
		
		//repeat for the y co-ordinates
		if(person.yFlag == true)
		{
			person.yCoord++;//we have not hit the bottom yet
		}
		else
		{
			//if this is true, we're heading back to top
			person.yCoord--;
		}
		
		if(person.yCoord == HEIGHT - person.diameter)
		{
			//if true, we've hit bottom, flip the flag
			person.yFlag = false;
		}
		if(person.yCoord == 0)
		{
			//if true, we're at the top, flip the flag
			person.yFlag = true;
		}
	}//end calcPosition
	
}
