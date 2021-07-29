import java.awt.Color;

/**
* Program Name: People.java
* Purpose: People class for the epidemic simulator
* Coder: Katherine Argall, Roland Turner, and Scott Catton
* Date: Jul. 29, 2021
*/

public class People
{
	
	boolean isAlive;
	boolean isInfected;
	int immunityStatus;
	Color colour;
	int xCoord;
	int yCoord;
	int xIncrement;
	int yIncrement;
	int counter;
	int diameter;
	boolean xFlag;
	boolean yFlag;
	
	public People() {
		this.isAlive = true;
		this.isInfected = false;
		this.colour = Color.blue;
		this.xCoord = (int)Math.random();
		this.yCoord = (int)Math.random();
		this.xIncrement = 5;
		this.yIncrement = 5;
		this.counter = 0;
		this.diameter = 5;
		this.xFlag = true;
		this.yFlag = true;
	}

	/**
	 * Getter for diameter
	 */
	public int getDiameter()
	{
		return diameter;
	}

	/**
	 * Setter for diameter
	 */
	public void setDiameter(int diameter)
	{
		this.diameter = diameter;
	}

	/**
	 * Getter for xFlag
	 */
	public boolean isxFlag()
	{
		return xFlag;
	}

	/**
	 * Setter for xFlag
	 */
	public void setxFlag(boolean xFlag)
	{
		this.xFlag = xFlag;
	}

	/**
	 * Getter for yFlag
	 */
	public boolean isyFlag()
	{
		return yFlag;
	}

	/**
	 * Setter for yFlag
	 */
	public void setyFlag(boolean yFlag)
	{
		this.yFlag = yFlag;
	}

	/**
	 * Getter for isAlive
	 */
	public boolean isAlive()
	{
		return isAlive;
	}

	/**
	 * Setter for isAlive
	 */
	public void setAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}

	/**
	 * Getter for isInfected
	 */
	public boolean isInfected()
	{
		return isInfected;
	}

	/**
	 * Setter for isInfected
	 */
	public void setInfected(boolean isInfected)
	{
		this.isInfected = isInfected;
	}

	/**
	 * Getter for immunityStatus
	 */
	public int getImmunityStatus()
	{
		return immunityStatus;
	}

	/**
	 * Setter for immunityStatus
	 */
	public void setImmunityStatus(int immunityStatus)
	{
		this.immunityStatus = immunityStatus;
	}

	/**
	 * Getter for colour
	 */
	public Color getColour()
	{
		return colour;
	}

	/**
	 * Setter for colour
	 */
	public void setColour(Color colour)
	{
		this.colour = colour;
	}

	/**
	 * Getter for xCoord
	 */
	public int getxCoord()
	{
		return xCoord;
	}

	/**
	 * Setter for xCoord
	 */
	public void setxCoord(int xCoord)
	{
		this.xCoord = xCoord;
	}

	/**
	 * Getter for yCoord
	 */
	public int getyCoord()
	{
		return yCoord;
	}

	/**
	 * Setter for yCoord
	 */
	public void setyCoord(int yCoord)
	{
		this.yCoord = yCoord;
	}

	/**
	 * Getter for xIncrement
	 */
	public int getxIncrement()
	{
		return xIncrement;
	}

	/**
	 * Setter for xIncrement
	 */
	public void setxIncrement(int xIncrement)
	{
		this.xIncrement = xIncrement;
	}

	/**
	 * Getter for yIncrement
	 */
	public int getyIncrement()
	{
		return yIncrement;
	}

	/**
	 * Setter for yIncrement
	 */
	public void setyIncrement(int yIncrement)
	{
		this.yIncrement = yIncrement;
	}

	/**
	 * Getter for counter
	 */
	public int getCounter()
	{
		return counter;
	}

	/**
	 * Setter for counter
	 */
	public void setCounter(int counter)
	{
		this.counter = counter;
	}
	
	

}
