package main;

import java.awt.*;
import javax.swing.*;
import viewAndController.*;

/**The IsMoMeMain class starts the program. In the IsMoMeMain class the JFrame is created, 
 * on which the different panels are called up during the course of the program.
 * To call up the different panels, the CardLayout is used.
 */
public class IsMoMeMain extends JFrame{
	private static final long serialVersionUID = 1L;

	//variables
	public static JFrame frame;
	public static JPanel panelCont = new JPanel();
	public static CardLayout cl = new CardLayout();
	public static viewAndController.WelcomePanel wp;
	public static viewAndController.QuestionPanel qp;

	public static void main(String[] args) {
		//set JFrame
		frame = new JFrame("IsMoMe Berufs-Informations-Test"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(950,675);	

		//set Panels 
		panelCont.setLayout(cl);

		//declare Panel 1 and 2 (Panel 3 later)
		wp = new WelcomePanel();
		panelCont.add(wp, "1");
		qp = new QuestionPanel();
		panelCont.add(qp, "2");

		//first panel = WelcomePanel
		cl.show(panelCont, "1");				

		//add Panel to Frame and set visible
		frame.add(panelCont);
		frame.setResizable(false);
		frame.setVisible(true); 
		frame.setLocationRelativeTo(null);		
	}

}