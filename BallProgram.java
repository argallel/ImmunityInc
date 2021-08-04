import javax.swing.*;
import javax.swing.border.BevelBorder;

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
	public static int infected, nonvacInfec, partvacInfec, fullyvacIfec, recovered, dead;
	public static int contracted, unvacContracted, partvacContracted, fullvacContracted, natContracted, totRecovered, unvacDead, partvacDead, fullvacDead, natDead;
	
	
	public static TrackerView track;

	public static void setNatDead(int natDead)
	{
		BallProgram.natDead = natDead;
	}

	public BallProgram(){
		infected = 1;
		nonvacInfec = 1;
		partvacInfec = 0;
		fullyvacIfec = 0;
		recovered = 0;
		dead = 0;
		contracted = 0;
		unvacContracted = 0;
		partvacContracted = 0; 
		fullvacContracted = 0;
		totRecovered = 0; 
		unvacDead = 0;
		partvacDead = 0;
		fullvacDead = 0;
		natDead = 0;
		natContracted = 0;
		
		peopleList = new Vector<People>();
		for(int i = 0; i <= (Integer)Program.peopleSpnr.getValue(); i++) {
			peopleList.add(new People());
		}
		
		//Deals with the percent of vaccinated and such
		int numOneShot =  (int) Math.round(peopleList.size() * (double)((int)Program.oneShotRateCB.getSelectedItem()/100.00));
		int numTwoShot =  (int) Math.round(peopleList.size() * (double)((int)Program.fullRateCB.getSelectedItem()/100.00));
		int natImmun =  (int) Math.round(peopleList.size() * (double)((int)Program.natImunCB.getSelectedItem()/100.00));
		
		//Immunity of 1 shot
		for(int i = 0; i < numOneShot; i++) {
			peopleList.get(i).setImmunityStatus(2);
			peopleList.get(i).setColour(Color.CYAN);
			peopleList.get(i).setInitialImmun(2);
		}
		
		//Immunity of 2 shot
		for(int i = numOneShot; i < numTwoShot + numOneShot; i++) {
			peopleList.get(i).setImmunityStatus(3);
			peopleList.get(i).setColour(Color.YELLOW);
			peopleList.get(i).setInitialImmun(3);
		}
		
		//Immunity of Natural
		for(int i = numTwoShot + numOneShot; i < numTwoShot + numOneShot + natImmun; i++) {
			peopleList.get(i).setImmunityStatus(4);
			peopleList.get(i).setColour(Color.GREEN);
			peopleList.get(i).setInitialImmun(4);
		}

		
		//Set one infectious person
		peopleList.get(peopleList.size() -1).setColour(Color.RED);
		peopleList.get(peopleList.size() -1).setInfected(true);
		peopleList.get(peopleList.size() -1).setEverInfec(true);
		peopleList.get(peopleList.size() -1).setInitialImmun(1);
		
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
				findTotals();
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
						p2.setEverInfec(true);
						this.infected++;
						this.nonvacInfec++;
					}
				}
				
				//One shot Immunity
				else if(p1.getImmunityStatus() == 2) {
					if(Math.random() <= 0.4) {
						p2.counter = 0;
						p2.setInfected(true);
						p2.setColour(Color.RED);
						p2.setEverInfec(true);
						this.infected++;
						this.partvacInfec++;
					}
				}
				
				//Two shot immunity
				else if(p1.getImmunityStatus() == 3) {
					if(Math.random() <= 0.1) {
						p2.counter = 0;
						p2.setInfected(true);
						p2.setColour(Color.RED);
						p2.setEverInfec(true);
						this.infected++;
						this.fullyvacIfec++;
					}
				}
				
				//Natural Immunity
				else if(p1.getImmunityStatus() == 4) {
					if(Math.random() <= 0.1) {
						p2.counter = 0;
						p2.setInfected(true);
						p2.setColour(Color.RED);
						p2.setEverInfec(true);
						this.infected++;
						
						if(p2.initialImmun == 4) {
							natContracted++;
						}
					}
				}
				
			}
			else if(p2.isInfected == true && p1.isAlive == true && p1.isInfected == false ) {
				if(p1.getImmunityStatus() == 1) {
					if(Math.random() <= 0.8) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
						p1.setEverInfec(true);
						this.infected++;
						this.nonvacInfec++;
					}
				}
				
				//One shot Immunity
				else if(p1.getImmunityStatus() == 2) {
					if(Math.random() <= 0.4) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
						p1.setEverInfec(true);
						this.infected++;
						this.partvacInfec++;
					}
				}
				
				//Two shot immunity
				else if(p1.getImmunityStatus() == 3) {
					if(Math.random() <= 0.1) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
						p1.setEverInfec(true);
						this.infected++;
						this.fullyvacIfec++;
					}
				}
				
				//Natural Immunity
				else if(p1.getImmunityStatus() == 4) {
					if(Math.random() <= 0.1) {
						p1.counter = 0;
						p1.setInfected(true);
						p1.setColour(Color.RED);
						p1.setEverInfec(true);
						this.infected++;
						
						if(p2.initialImmun == 4) {
							natContracted++;
						}
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
					this.infected--;
					this.nonvacInfec++;
					this.dead++;
					
				}
				else if(ball.getImmunityStatus() == 2 && Math.random() < 0.05) {
					ball.setAlive(false);
					ball.setColour(Color.BLACK);
					this.infected--;
					this.dead++;
				}
				else if(ball.getImmunityStatus() == 3 && Math.random() < 0.01) {
					ball.setAlive(false);
					ball.setColour(Color.BLACK);
					this.infected--;
					this.dead++;
				}
				if(ball.getImmunityStatus() == 4 && Math.random() < 0.003) {
					ball.setAlive(false);
					ball.setColour(Color.BLACK);
					this.infected--;
					this.dead++;
				}
				else {
					ball.setInfected(false);
					ball.colour = Color.GREEN;
					ball.setImmunityStatus(4);
					this.infected--;
					this.recovered++;
					if(ball.initialImmun == 1) {
						this.nonvacInfec--;
					}
					else if(ball.initialImmun == 2) {
						this.partvacInfec--;
					}
					else if(ball.initialImmun == 3) {
						this.fullyvacIfec--;
					}
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
		track.update();
	}//end calcPosition
	
	public void findTotals() {
		for(int i = 0; i <= (Integer)Program.peopleSpnr.getValue(); i++) {
			if(peopleList.get(i).everInfec == true) {
				contracted++;
				if(peopleList.get(i).initialImmun == 1) {
					unvacContracted++;
				}
				else if(peopleList.get(i).initialImmun == 2) {
					partvacContracted++;
				}
				else if(peopleList.get(i).initialImmun == 3) {
					fullvacContracted++;
				}
				if(peopleList.get(i).everInfec == true && peopleList.get(i).isInfected == false) {
					totRecovered++;
				}
				else if (peopleList.get(i).isAlive == false || peopleList.get(i).initialImmun == 1) {
					unvacDead++;
				}
				else if (peopleList.get(i).isAlive == false || peopleList.get(i).initialImmun == 2) {
					partvacDead++;
				}
				else if (peopleList.get(i).isAlive == false || peopleList.get(i).initialImmun == 3) {
					fullvacDead++;
				}
				else if (peopleList.get(i).isAlive == false || peopleList.get(i).initialImmun == 4) {
					natDead++;
				}
			}
		}
	}
	
	

	public static void main(String[] args)
	{
	// create a JFrame to hold the JPanel
			JFrame frame = new JFrame("Just Follow the Bouncing Ball");
			
			//boilerplate
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLayout(new BorderLayout() );//ANONYMOUS object
			frame.setSize(1200,1000);
			frame.setLocationRelativeTo(null);
			
			JPanel ballPit = new JPanel(new BorderLayout());
			ballPit.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Pandemic Simulator"));
			
			//create an ANONYMOUS object of the class and add the JPanel to the JFrame
			ballPit.add(new BallProgram(), BorderLayout.CENTER);
			
			
			frame.add(ballPit);
			
			frame.pack();//shrinks the JFrame to the smallest size possible to conserve
			             //screen real estate. Comment it out to see its effect
			frame.setVisible(true);	
			track = new TrackerView();
	}

}
