import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* Program Name: People.java
* Purpose: People class for the epidemic simulator
* Coder: Katherine Argall, Roland Turner, and Scott Catton
* Date: Jul. 29, 2021
*/

public class AboutView extends JFrame 
{	
	public static void main(String[] args) {
		// create a JFrame to hold the JPanel
		JFrame frame = new JFrame("Immunity Inc - Help and About page");
		
		//boilerplate
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout() );//ANONYMOUS object
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);	
	}

}
