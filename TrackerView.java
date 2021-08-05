/**
* Program Name: TrackerView.java
* Purpose: Put Something useful Here
* Coder: You
* Date: Aug. 4, 2021
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.event.*;

public class TrackerView extends JFrame
{
	
	JLabel infected, nonvacInfec, partvacInfec, fullyvacInfec, recovered, dead, contracted, totRecovered, totDead, unvacContracted, partvacContracted, fullvacContracted, unvacDead, partvacDead, fullvacDead, natDead;
	
	public TrackerView() {
		super("Immunity Inc. Tracking Information");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(470,550);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		//Live Tracking
		JLabel infectedL = new JLabel("Current Infected: ", JLabel.RIGHT);
		JLabel nonvacInfecL = new JLabel("     Current Nonvaccinated Infected: ", JLabel.RIGHT);
		JLabel partvacInfecL = new JLabel("Current One Shot Infected: ", JLabel.RIGHT);
		JLabel fullyvacInfecL = new JLabel("Current Two Shot Infected: ", JLabel.RIGHT);
		JLabel recoveredL = new JLabel("Current Total Recovered: ", JLabel.RIGHT);
		JLabel deadL = new JLabel("Current Dead: ", JLabel.RIGHT);
		
		JPanel body = new JPanel(new FlowLayout(20,20,20));
		
		infected = new JLabel(Integer.toString(BallProgram.infected));
		nonvacInfec = new JLabel(Integer.toString(BallProgram.nonvacInfec));
		partvacInfec = new JLabel(Integer.toString(BallProgram.partvacInfec));
		fullyvacInfec = new JLabel(Integer.toString(BallProgram.fullyvacIfec));
		recovered = new JLabel(Integer.toString(BallProgram.recovered));
		dead = new JLabel(Integer.toString(BallProgram.dead));
		
		JPanel live = new JPanel(new GridLayout(6, 2, 5, 5));
		live.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Live Information"));
		
		live.add(infectedL);
		live.add(infected);
		live.add(nonvacInfecL);
		live.add(nonvacInfec);
		live.add(partvacInfecL);
		live.add(partvacInfec);
		live.add(fullyvacInfecL);
		live.add(fullyvacInfec);
		live.add(recoveredL);
		live.add(recovered);
		live.add(deadL);
		live.add(dead);
		
		body.add(live);
		
		//Totals
		JLabel contractedL = new JLabel("Total Infected: ", JLabel.RIGHT);
		JLabel totRecoveredL = new JLabel("Total Recovered: ", JLabel.RIGHT);
		JLabel totDeadL = new JLabel("Total Dead: ", JLabel.RIGHT);
		
		JLabel unvacContractedL = new JLabel("Percent Infected - Unvaccinated: ", JLabel.RIGHT);
		JLabel partvacContractedL = new JLabel("Percent Infected - One Shot: ", JLabel.RIGHT);
		JLabel fullvacContractedL = new JLabel("Percent Infected - Two Shot: ", JLabel.RIGHT);
		
		JLabel unvacDeadL = new JLabel("Percent Dead - Unvaccinated: ", JLabel.RIGHT);
		JLabel partvacDeadL = new JLabel("Percent Dead - One Shot: ", JLabel.RIGHT);
		JLabel fullvacDeadL = new JLabel("Percent Dead - Two Shot: ", JLabel.RIGHT);
		JLabel natDeadL = new JLabel("    Percent Dead - Natural Immunity: ", JLabel.RIGHT);
		
		contracted = new JLabel("-");
		totRecovered = new JLabel("-");
		totDead = new JLabel("-");
		
		unvacContracted = new JLabel("-");
		partvacContracted = new JLabel("-");
		fullvacContracted = new JLabel("-");
		
		unvacDead = new JLabel("-");
		partvacDead = new JLabel("-");
		fullvacDead = new JLabel("-");
		natDead = new JLabel("-");
		
		JPanel totals = new JPanel(new GridLayout(10, 2, 5, 5));
		totals.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Final Information"));
		
		totals.add(contractedL);
		totals.add(contracted);
		totals.add(totRecoveredL);
		totals.add(totRecovered);
		totals.add(totDeadL);
		totals.add(totDead);
		totals.add(unvacContractedL);
		totals.add(unvacContracted);
		totals.add(partvacContractedL);
		totals.add(partvacContracted);
		totals.add(fullvacContractedL);
		totals.add(fullvacContracted);
		totals.add(unvacDeadL);
		totals.add(unvacDead);
		totals.add(partvacDeadL);
		totals.add(partvacDead);
		totals.add(fullvacDeadL);
		totals.add(fullvacDead);
		totals.add(natDeadL);
		totals.add(natDead);
		
		body.add(totals);
		
		 BufferedImage image = null;
		try {
			image = ImageIO.read(new File("../ImmunityInc/src/fulllogofitted.JPG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel flowcenter = new JPanel(new FlowLayout());	 
		JLabel imageHeader = new JLabel(new ImageIcon(image), JLabel.CENTER);		 
		flowcenter.add(imageHeader);
		flowcenter.setBorder(new EmptyBorder(20, 5, 5, 5));
		 
		this.add(flowcenter, BorderLayout.NORTH);		
		this.add(body, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void update() {
		infected.setText(Integer.toString(BallProgram.infected));
		nonvacInfec.setText(Integer.toString(BallProgram.nonvacInfec));
		partvacInfec.setText(Integer.toString(BallProgram.partvacInfec));
		fullyvacInfec.setText(Integer.toString(BallProgram.fullyvacIfec));
		recovered.setText(Integer.toString(BallProgram.recovered));
		dead.setText(Integer.toString(BallProgram.dead));
		
		contracted.setText(Integer.toString(BallProgram.contracted));
		totRecovered.setText(Integer.toString(BallProgram.totRecovered));
		totDead.setText(Integer.toString(BallProgram.dead));
		
		unvacContracted.setText(Integer.toString(BallProgram.unvacContracted));
		partvacContracted.setText(Integer.toString(BallProgram.partvacContracted));
		fullvacContracted.setText(Integer.toString(BallProgram.fullvacContracted));
		
		if(BallProgram.unvacContracted != 0){
			unvacDead.setText(Double.toString(BallProgram.unvacDead / (double)BallProgram.unvacContracted));
		}
		if(BallProgram.partvacContracted != 0) {
			partvacDead.setText(Double.toString(BallProgram.partvacDead / (double)BallProgram.partvacContracted));
		}
		if(BallProgram.fullvacContracted != 0) {
			fullvacDead.setText(Double.toString(BallProgram.fullvacDead / (double)BallProgram.fullvacContracted));
		}
		if(BallProgram.natContracted != 0) {
			natDead.setText(Double.toString(BallProgram.natDead / (double)BallProgram.natContracted));
		}
		if(BallProgram.contracted != 0) {
			unvacContracted.setText(Double.toString((double)BallProgram.unvacContracted / BallProgram.contracted));
			partvacContracted.setText(Double.toString((double)BallProgram.partvacContracted / BallProgram.contracted));
			fullvacContracted.setText(Double.toString((double)BallProgram.fullvacContracted / BallProgram.contracted));
		}
	}

	public static void main(String[] args)
	{
		new TrackerView();

	}

}
