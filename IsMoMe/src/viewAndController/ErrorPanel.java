package viewAndController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ErrorPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//variables
	private JButton okay;
	private JLabel advice, miniLogo;
	private TheMiniListener lis = new TheMiniListener();
	
	public ErrorPanel() {
		//set layout to "null" to be able to position components with .setBounds()
		setLayout(null);
		
		//set Background of Panel
		setBackground(Color.white);

		//create & add logo
		Icon pic = new ImageIcon(getClass().getResource("miniLOGO.png"));
		miniLogo = new JLabel(pic);
		miniLogo.setBounds(10,10,300,70);
		add(miniLogo);
		
		//create & add text & check if user selected answer or typed in name
		if (WelcomePanel.error == 0) {
			advice = new JLabel("Bitte wähle eine Antwort aus!");		// text for not selceted answer
		} else if (WelcomePanel.error == 1) {
			advice = new JLabel("Bitte gebe deinen Namen ein!");		// text for missing name
		}
		advice.setFont(new Font ("Times New Roman", Font.PLAIN, 16));
		advice.setBounds(65,50,200,100);
		add(advice);
		
		//create & add OK-button
		okay = new JButton("Okay");
		okay.setBounds(115,120,80,40);
		add(okay);
		okay.addActionListener(lis);		
	}

	
	/**TheMiniListener is the ActionListener for the ErrorPanel.
	 * It reacts to the selection of the okay-button and shows the advice.
	 */
	private class TheMiniListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==okay) {
				if (WelcomePanel.error == 0) {			
					QuestionPanel.miniFrame.setVisible(false);	
				} else if (WelcomePanel.error == 1) {
					WelcomePanel.miniFrame.setVisible(false);
				}
			}
		}
	}

		
}