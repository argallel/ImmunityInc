import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.awt.*;

/**
* Program Name: Program.java
* Purpose: Running point of the epidemic simulator program
* Coder: Katherine Argall, Roland Turner, and Scott Catton
* Date: Jul. 29, 2021
*/

public class Program extends JFrame {
	
	private JButton startPanBtn, pausePanBtn, aboutPanBtn;
	private JLabel title, peopleLbl, natImunLbl, unvaxLbl, unvaxMathLbl, oneShotLbl, fullLbl, blank;
	public static JComboBox<Integer> peopleCB, unvaxRateCB, oneShotRateCB, fullRateCB, natImunCB;
	public static JSpinner peopleSpnr;
	
	public Program() {
		super("Immunity Inc - Pandemic Simulation Application");
		
		PanListener listner = new PanListener();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,420);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		title = new JLabel("Please enter your desired percentages of vaccination", JLabel.CENTER);
		title.setBorder(new EmptyBorder(10,10,10,10));
		this.add(title, BorderLayout.NORTH);
		
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(6,2,8,8));
		body.setBorder(new EmptyBorder(10,30,10,30));
		this.add(body, BorderLayout.CENTER);
		
		Vector<Integer> numbers = new Vector<Integer>();
		
		 for (int i = 0; i <= 100; i += 5) {
			 numbers.add(i);
		 }
		 
		 peopleLbl = new JLabel("Total number of people in Simulation:", JLabel.RIGHT);
		 natImunLbl = new JLabel("Number of people with natural immunity:", JLabel.RIGHT);
		 unvaxLbl = new JLabel("Number of people unvaccinated:", JLabel.RIGHT);
		 oneShotLbl = new JLabel("Percent of people with one shot:", JLabel.RIGHT);
		 fullLbl = new JLabel("Percent of people fully vaccinated:", JLabel.RIGHT);
		 unvaxMathLbl = new JLabel("0", JLabel.LEFT);
		 
		 // Start Value, Min Value, Max Value, Step Value
		 SpinnerNumberModel model = new SpinnerNumberModel(100, 100, 10000, 100); 
		 peopleSpnr = new JSpinner(model);
		 peopleSpnr.setEditor(new JSpinner.DefaultEditor(peopleSpnr));
		 peopleSpnr.addChangeListener(listner);
		 
		 natImunCB = new JComboBox<Integer>(numbers);
		 natImunCB.addActionListener(listner);
		 oneShotRateCB = new JComboBox<Integer>(numbers);
		 oneShotRateCB.addActionListener(listner);
		 fullRateCB = new JComboBox<Integer>(numbers);
		 fullRateCB.addActionListener(listner);
		 
		 startPanBtn = new JButton("Cause a Pandemic");
		 startPanBtn.addActionListener(listner);
		 
		 BufferedImage image = null;
		try {
			image = ImageIO.read(new File("../ImmunityInc/src/fulllogofitted.JPG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 blank = new JLabel(new ImageIcon(image));
		 
		 body.add(peopleLbl);
		 body.add(peopleSpnr);
		 body.add(natImunLbl);
		 body.add(natImunCB);
		 body.add(oneShotLbl);
		 body.add(oneShotRateCB);
		 body.add(fullLbl);
		 body.add(fullRateCB);
		 body.add(unvaxLbl);
		 body.add(unvaxMathLbl);
		 body.add(blank);
		 body.add(startPanBtn);
		 
		 JPanel footer = new JPanel(new FlowLayout());
		 
		 aboutPanBtn = new JButton("About/Help");
		 aboutPanBtn.addActionListener(listner);
		 footer.add(aboutPanBtn);
		 
		 this.add(footer, BorderLayout.SOUTH);
		 		
		this.setVisible(true);
	}
	
	
	 private class PanListener implements ActionListener, ChangeListener {

			//Name: Action Performed
			//Description: Checks data and starts the simulation
			//Inputs: 2 People
			//Outputs: none
		@Override
		public void actionPerformed(ActionEvent e) {
			
			double natImun = Double.parseDouble(natImunCB.getSelectedItem().toString());
			double oneShot = Double.parseDouble(oneShotRateCB.getSelectedItem().toString());
			double twoShot = Double.parseDouble(fullRateCB.getSelectedItem().toString());
			double totalPeople = Double.parseDouble(peopleSpnr.getValue().toString());
			
			if (e.getSource().equals(startPanBtn)) {
				//Checks totals are less than 100
				if (oneShot + twoShot > 100) {
					JFrame warning = new JFrame();
					JOptionPane.showMessageDialog(warning, "Total percentage of one shot and fully vaccinated must be less then or equal to 100");
				}
				else {				
						BallProgram.main(null);	
				}
			}
			
			if (e.getSource().equals(aboutPanBtn)) {
				AboutView.main(null);
			}
			//Show number of unvaccinated people
			if (e.getSource().equals(oneShotRateCB) || e.getSource().equals(fullRateCB) || e.getSource().equals(natImunCB)) {
				unvaxMathLbl.setText("" + (totalPeople - (totalPeople * (oneShot + twoShot + natImun)/100)));
			}
			
			if (totalPeople - (totalPeople * (oneShot + twoShot + natImun)/100) < 0) {
				unvaxMathLbl.setText("Invalid percentages");
			}
			
		}

		//Name: State Changed
		//Description: Checks percentages are under 100
		//Inputs: Event
		//Outputs: none
		@Override
		public void stateChanged(ChangeEvent e) {
			
			double natImun = Double.parseDouble(natImunCB.getSelectedItem().toString());
			double oneShot = Double.parseDouble(oneShotRateCB.getSelectedItem().toString());
			double twoShot = Double.parseDouble(fullRateCB.getSelectedItem().toString());
			double totalPeople = Double.parseDouble(peopleSpnr.getValue().toString());
			
			if (e.getSource().equals(peopleSpnr)) {
				unvaxMathLbl.setText("" + (totalPeople - (totalPeople * (oneShot + twoShot + natImun)/100)));
			}
			
			if (totalPeople - (totalPeople * (oneShot + twoShot + natImun)/100) < 0) {
				unvaxMathLbl.setText("Invalid percentages");
			}
		}
	 }
	
	public static void main(String[] args){
		new Program();
	}
}
