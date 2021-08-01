import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
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
	
	private JButton startPanBtn, pausePanBtn;
	private JLabel title, peopleLbl, unvaxLbl, unvaxMathLbl, oneShotLbl, fullLbl;
	public static JComboBox<Integer> peopleCB, unvaxRateCB, oneShotRateCB, fullRateCB;
	public static JSpinner peopleSpnr;
	private JPanel blank;
	
	public Program() {
		super("Immunity Inc - Pandemic Simulation Application");
		
		PanListener listner = new PanListener();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,300);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		title = new JLabel("Please enter your desired percentages of vaccination", JLabel.CENTER);
		title.setBorder(new EmptyBorder(10,10,10,10));
		this.add(title, BorderLayout.NORTH);
		
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(5,2,8,8));
		body.setBorder(new EmptyBorder(10,10,10,10));
		this.add(body, BorderLayout.CENTER);
		
		Vector<Integer> numbers = new Vector<Integer>();
		
		 for (int i = 0; i <= 100; i += 5) {
			 numbers.add(i);
		 }
		 
		 peopleLbl = new JLabel("Total number of people in Simulation:", JLabel.RIGHT);
		 unvaxLbl = new JLabel("Number of people unvaccinated:", JLabel.RIGHT);
		 oneShotLbl = new JLabel("Percent of people with one shot:", JLabel.RIGHT);
		 fullLbl = new JLabel("Percent of people fully vaccinated:", JLabel.RIGHT);
		 unvaxMathLbl = new JLabel("0", JLabel.LEFT);
		 
		 // Start Value, Min Value, Max Value, Step Value
		 SpinnerNumberModel model = new SpinnerNumberModel(100, 100, 10000, 100); 
		 peopleSpnr = new JSpinner(model);
		 peopleSpnr.setEditor(new JSpinner.DefaultEditor(peopleSpnr));
		 peopleSpnr.addChangeListener(listner);
		 
		 //peopleCB = new JComboBox<Integer>(numbers);
		 //unvaxRateCB = new JComboBox<Integer>(numbers);
		 oneShotRateCB = new JComboBox<Integer>(numbers);
		 oneShotRateCB.addActionListener(listner);
		 fullRateCB = new JComboBox<Integer>(numbers);
		 fullRateCB.addActionListener(listner);
		 
		 startPanBtn = new JButton("Cause a Pandemic");
		 startPanBtn.addActionListener(listner);;
		 
		 blank = new JPanel();
		 
		 body.add(peopleLbl);
		 body.add(peopleSpnr);
		 body.add(oneShotLbl);
		 body.add(oneShotRateCB);
		 body.add(fullLbl);
		 body.add(fullRateCB);
		 body.add(unvaxLbl);
		 body.add(unvaxMathLbl);
		 body.add(blank);
		 body.add(startPanBtn);
		 		
		this.setVisible(true);
	}
	
	 private class PanListener implements ActionListener, ChangeListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			double oneShot = Double.parseDouble(oneShotRateCB.getSelectedItem().toString());
			double twoShot = Double.parseDouble(fullRateCB.getSelectedItem().toString());
			double totalPeople = Double.parseDouble(peopleSpnr.getValue().toString());
			
			if (e.getSource().equals(startPanBtn)) {
				
				if (oneShot + twoShot > 100) {
					JFrame warning = new JFrame();
					JOptionPane.showMessageDialog(warning, "Total percentage of one shot and fully vaccinated must be less then or equal to 100");
				}
				else {
					
						BallProgram.main(null);
					
					
				}
			}
			
			if (e.getSource().equals(oneShotRateCB) || e.getSource().equals(fullRateCB)) {
				unvaxMathLbl.setText("" + (totalPeople - (totalPeople * (oneShot + twoShot)/100)));
			}
			
			if (totalPeople - (totalPeople * (oneShot + twoShot)/100) < 0) {
				unvaxMathLbl.setText("Invalid percentages");
			}
			
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			
			double oneShot = Double.parseDouble(oneShotRateCB.getSelectedItem().toString());
			double twoShot = Double.parseDouble(fullRateCB.getSelectedItem().toString());
			double totalPeople = Double.parseDouble(peopleSpnr.getValue().toString());
			
			if (e.getSource().equals(peopleSpnr)) {
				unvaxMathLbl.setText("" + (totalPeople - (totalPeople * (oneShot + twoShot)/100)));
			}
			
			if (totalPeople - (totalPeople * (oneShot + twoShot)/100) < 0) {
				unvaxMathLbl.setText("Invalid percentages");
			}
		}
	 }
	
	public static void main(String[] args){
		new Program();
	}
}
