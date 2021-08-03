import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.event.*;

/**
* Program Name: Program.java
* Purpose: Ball bouncer program for the epidemic simulator
* Coder: Katherine Argall, Roland Turner, and Scott Catton
* Date: Jul. 29, 2021
*/


public class BallProgram extends JPanel
{
	
	public final static int WIDTH = 700, HEIGHT = 500;
	private final int LAG_TIME = 200; 
	private static Timer time;
	private final int IMG_DIM = 8;
	private Vector<People> peopleList;
	private static int counter;
	
	

	public BallProgram(){
		peopleList = new Vector<People>();
		for(int i = 0; i <= (Integer)Program.peopleSpnr.getValue(); i++) {
			peopleList.add(new People());
		}
		
		//Deals with the percent of vaccinated and such
		int numOneShot =  (int) Math.round(peopleList.size() * (double)((int)Program.oneShotRateCB.getSelectedItem()/100.00));
		int numTwoShot =  (int) Math.round(peopleList.size() * (double)((int)Program.fullRateCB.getSelectedItem()/100.00));
		//int natImmun =  (int) Math.round(peopleList.size() * (double)((int)Program.naturalImmunityCB.getSelectedItem()/100.00));
		
		//Immunity of 1 shot
		for(int i = 0; i < numOneShot; i++) {
			peopleList.get(i).setImmunityStatus(2);
			peopleList.get(i).setColour(Color.CYAN);
		}
		
		//Immunity of 2 shot
		for(int i = numOneShot; i < numTwoShot + numOneShot; i++) {
			peopleList.get(i).setImmunityStatus(3);
			peopleList.get(i).setColour(Color.YELLOW);
		}
		
		//Immunity of Natural
//		for(int i = numTwoShot; i < numTwoShot + numOneShot + natImmun; i++) {
//			peopleList.get(i).setImmunityStatus(4);
//			peopleList.get(i).setColour(Color.GREEN);
//		}

		
		//Set one infectious person
		peopleList.get(peopleList.size() -1).setColour(Color.RED);
		peopleList.get(peopleList.size() -1).setInfected(true);
		
		this.time = new Timer(LAG_TIME, new BounceListener() );
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT) );
		
		this.time.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i <= (Integer)Program.peopleSpnr.getValue(); i++) {
			g.setColor(peopleList.get(i).getColour());
			g.fillOval(peopleList.get(i).getxCoord(), peopleList.get(i).getyCoord(), peopleList.get(i).getDiameter(), peopleList.get(i).getDiameter());
		}
	}
	
	private class BounceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			counter++;
			if(counter == 450) {
				BallProgram.time.stop();
			}
			for(int i = 0; i <= (Integer)Program.peopleSpnr.getValue(); i++) {
				calcPosition(peopleList.get(i));
				for(int j = 0; j <= (Integer)Program.peopleSpnr.getValue(); j++) {
					if(i != j) {
						collisionDetection(peopleList.get(i), peopleList.get(j));
					}
				}
			}
		}
	}
	
	public void collisionDetection(People p1, People p2) {
		int deltaX = p1.getxCoord() - p2.getxCoord();
		int deltaY = p1.getyCoord() - p2.getyCoord();
		
		if(Math.sqrt(deltaX *deltaX + deltaY * deltaY) < IMG_DIM) {
			p1.setxFlag(!p1.xFlag);
			p1.setyFlag(!p1.yFlag);
			p2.setxFlag(!p2.xFlag);
			p2.setyFlag(!p2.yFlag);
			
			if(p1.isInfected == true && p2.isAlive == true && p2.isInfected == false) {
				//No immunity
				if(p1.getImmunityStatus() == 1) {
					if(Math.random() <= 0.8) {
						p2.counter = 0;
						p2.setInfected(true);
						p2.setColour(Color.RED);
					}
				}
				
				//One shot Immunity
				else if(p1.getImmunityStatus() == 2) {
					if(Math.random() <= 0.4) {
						p2.counter = 0;
						p2.setInfected(true);
						p2.setColour(Color.RED);
					}
				}
				
				//Two shot immunity
				else if(p1.getImmunityStatus() == 3) {
					if(Math.random() <= 0.1) {
						p2.counter = 0;
						p2.setInfected(true);
						p2.setColour(Color.RED);
					}
				}
				
				//Natural Immunity
				else if(p1.getImmunityStatus() == 4) {
					if(Math.random() <= 0.1) {
						p2.counter = 0;
						p2.setInfected(true);
						p2.setColour(Color.RED);
					}
				}
				
			}
			else if(p2.getColour() == Color.RED && p1.isAlive == true) {
				if(p1.getImmunityStatus() == 1) {
					if(Math.random() <= 0.8) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
					}
				}
				
				//One shot Immunity
				else if(p1.getImmunityStatus() == 2) {
					if(Math.random() <= 0.4) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
					}
				}
				
				//Two shot immunity
				else if(p1.getImmunityStatus() == 3) {
					if(Math.random() <= 0.1) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
					}
				}
				
				//Natural Immunity
				else if(p1.getImmunityStatus() == 4) {
					if(Math.random() <= 0.1) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
					}
				}
			}
			
			repaint();
		}
	}
	
	public void calcPosition(People ball)
	{
		if(ball.isInfected == true) {
			ball.counter++;
			if(ball.counter == 150) {
				if(ball.getImmunityStatus() == 1 && Math.random() < 0.1) {
					ball.setAlive(false);
					ball.setColour(Color.BLACK);
				}
				else if(ball.getImmunityStatus() == 2 && Math.random() < 0.05) {
					ball.setAlive(false);
					ball.setColour(Color.BLACK);
				}
				else if(ball.getImmunityStatus() == 3 && Math.random() < 0.01) {
					ball.setAlive(false);
					ball.setColour(Color.BLACK);
				}
				if(ball.getImmunityStatus() == 4 && Math.random() < 0.003) {
					ball.setAlive(false);
					ball.setColour(Color.BLACK);
				}
				else {
					ball.setInfected(false);
					ball.colour = Color.GREEN;
					ball.setImmunityStatus(4);
				}
			}			
		}
		
		if(ball.isAlive == true) {
			if(ball.xFlag == true)
			{
				ball.xCoord++;//we have not hit the right side yet so increment xCoord
			}
			else
			{
				//if this is true, we're heading left
				ball.xCoord--;
			}
			
			if(ball.xCoord == WIDTH - ball.diameter)
			{
				//if true, we've hit right edge, flip the flag
				ball.xFlag = false;
			}
			if(ball.xCoord == 0)
			{
				//if true, we're at left edge, flip the flag
				ball.xFlag = true;
			}
			
			//repeat for the y co-ordinates
			if(ball.yFlag == true)
			{
				ball.yCoord++;//we have not hit the bottom yet
			}
			else
			{
				//if this is true, we're heading back to top
				ball.yCoord--;
			}
			
			if(ball.yCoord == HEIGHT - ball.diameter)
			{
				//if true, we've hit bottom, flip the flag
				ball.yFlag = false;
			}
			if(ball.yCoord == 0)
			{
				//if true, we're at the top, flip the flag
				ball.yFlag = true;
			}
		}
		else {
			ball.setColour(Color.BLACK);
		}
	}//end calcPosition

	public static void main(String[] args)
	{
	// create a JFrame to hold the JPanel
			JFrame frame = new JFrame("Just Follow the Bouncing Ball");
			
			//boilerplate
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new FlowLayout() );//ANONYMOUS object
			frame.setSize(1200,1000);
			frame.setLocationRelativeTo(null);
			
			
			//create an ANONYMOUS object of the class and add the JPanel to the JFrame
			frame.add(new BallProgram() );
			
			frame.pack();//shrinks the JFrame to the smallest size possible to conserve
			             //screen real estate. Comment it out to see its effect
			frame.setVisible(true);	
			
			
			

	}

}
