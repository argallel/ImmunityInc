import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.border.EmptyBorder;

public class AboutView extends JFrame
{
	public AboutView()
	{
		super("About Menu");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,290);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout() );
		this.setResizable(false);

		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1,5));
		titlePanel.setBorder(new EmptyBorder(10,50,10,50));
		
		JLabel katherineLabel = new JLabel("Katherine Argall");
		JLabel rolandLabel = new JLabel("Roland Turner", JLabel.CENTER);
		JLabel scottLabel = new JLabel("Scott Catton", JLabel.RIGHT);

		titlePanel.add(katherineLabel);
		titlePanel.add(new JLabel(""));
		titlePanel.add(rolandLabel); //float center
		titlePanel.add(new JLabel(""));
		titlePanel.add(scottLabel); //float right
		
		this.add(titlePanel, BorderLayout.NORTH);
		
		JPanel instructionsPanel = new JPanel();
		
		instructionsPanel.setLayout(new FlowLayout());
		
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
		flowcenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		 
		instructionsPanel.add(flowcenter, BorderLayout.NORTH);

		JTextArea txtPurpose = new JTextArea("  This is an educational program used to spread awarness on how a pandemic works for public   \n  safety purposes.");
		txtPurpose.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Purpose"));
		txtPurpose.setEditable(false);
		txtPurpose.setBackground(null);
		instructionsPanel.add(txtPurpose);
		
		JTextArea txtAbout = new JTextArea("  Fill in the fields to customize a simulated pandemic. Once you are happy with the settings, click\n  \"Cause Pandemic\" to begin the simulation.");
		txtAbout.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Instructions"));
		txtAbout.setEditable(false);
		txtAbout.setBackground(null);
		instructionsPanel.add(txtAbout);
		
		this.add(instructionsPanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new AboutView();
	}

}
