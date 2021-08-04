/**
* Program Name: TrackerView.java
* Purpose: Put Something useful Here
* Coder: You
* Date: Aug. 4, 2021
*/
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class TrackerView extends JFrame
{
	
	JTextField infected, nonvacInfec, partvacInfec, fullyvacInfec, recovered, dead, contracted, totRecovered, totDead, unvacContracted, partvacContracted, fullvacContracted, unvacDead, partvacDead, fullvacDead, natDead;
	
	public TrackerView() {
		super("I TRACK SHIT");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		//Live Tracking
		JLabel infectedL = new JLabel("Current Infected");
		JLabel nonvacInfecL = new JLabel("Current Nonvaccinated Infected");
		JLabel partvacInfecL = new JLabel("Current One Shot Infected");
		JLabel fullyvacInfecL = new JLabel("Current Two Shot Infected");
		JLabel recoveredL = new JLabel("Current Total Recovered");
		JLabel deadL = new JLabel("Current Dead");
		
		infected = new JTextField(Integer.toString(BallProgram.infected));
		infected.setEditable(false);
		nonvacInfec = new JTextField(Integer.toString(BallProgram.nonvacInfec));
		nonvacInfec.setEditable(false);
		partvacInfec = new JTextField(Integer.toString(BallProgram.partvacInfec));
		partvacInfec.setEditable(false);
		fullyvacInfec = new JTextField(Integer.toString(BallProgram.fullyvacIfec));
		fullyvacInfec.setEditable(false);
		recovered = new JTextField(Integer.toString(BallProgram.recovered));
		recovered.setEditable(false);
		dead = new JTextField(Integer.toString(BallProgram.dead));
		dead.setEditable(false);
		
		JPanel live = new JPanel(new GridLayout(6, 2, 5, 5));
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
		
		this.add(live, BorderLayout.NORTH);
		
		//Totals
		JLabel contractedL = new JLabel("Total Infected");
		JLabel totRecoveredL = new JLabel("Total Recovered");
		JLabel totDeadL = new JLabel("Total Dead");
		
		JLabel unvacContractedL = new JLabel("Percent Infected - Unvaccinated");
		JLabel partvacContractedL = new JLabel("Percent Infected - One Shot");
		JLabel fullvacContractedL = new JLabel("Percent Infected - Two Shot");
		
		JLabel unvacDeadL = new JLabel("Percent Dead - Unvaccinated");
		JLabel partvacDeadL = new JLabel("Percent Dead - One Shot");
		JLabel fullvacDeadL = new JLabel("Percent Dead - Two Shot");
		JLabel natDeadL = new JLabel("Percent Dead - Natural Immunity");
		
		contracted = new JTextField(Integer.toString(BallProgram.contracted));
		contracted.setEditable(false);
		totRecovered = new JTextField(Integer.toString(BallProgram.totRecovered));
		totRecovered.setEditable(false);
		totDead = new JTextField(Integer.toString(BallProgram.unvacContracted + BallProgram.partvacContracted + BallProgram.fullvacContracted + BallProgram.natDead));
		totDead.setEditable(false);
		
		unvacContracted = new JTextField(Integer.toString(BallProgram.unvacContracted));
		unvacContracted.setEditable(false);
		partvacContracted = new JTextField(Integer.toString(BallProgram.partvacContracted));
		partvacContracted.setEditable(false);
		fullvacContracted = new JTextField(Integer.toString(BallProgram.fullvacContracted));
		fullvacContracted.setEditable(false);
		
		unvacDead = new JTextField("-");
		unvacDead.setEditable(false);
		partvacDead = new JTextField("-");
		partvacDead.setEditable(false);
		fullvacDead = new JTextField("-");
		fullvacDead.setEditable(false);
		natDead = new JTextField("-");
		natDead.setEditable(false);
		
		JPanel totals = new JPanel(new GridLayout(10, 2, 5, 5));
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
		
		this.add(totals, BorderLayout.SOUTH);
		
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
		totDead.setText(Integer.toString(BallProgram.unvacContracted + BallProgram.partvacContracted + BallProgram.fullvacContracted + BallProgram.natDead));
		
		unvacContracted.setText(Integer.toString(BallProgram.unvacContracted));
		partvacContracted.setText(Integer.toString(BallProgram.partvacContracted));
		fullvacContracted.setText(Integer.toString(BallProgram.fullvacContracted));
		
		if(BallProgram.unvacContracted != 0 && BallProgram.partvacContracted != 0 && BallProgram.fullvacContracted != 0 && BallProgram.natContracted !=0){
			unvacDead.setText(Integer.toString(BallProgram.unvacDead / BallProgram.unvacContracted));
			partvacDead.setText(Integer.toString(BallProgram.partvacDead / BallProgram.partvacContracted));
			fullvacDead.setText(Integer.toString(BallProgram.fullvacDead / BallProgram.fullvacContracted));
			natDead.setText(Integer.toString(BallProgram.natDead / BallProgram.natContracted));
		}
		
	}

	public static void main(String[] args)
	{
		new TrackerView();

	}

}
