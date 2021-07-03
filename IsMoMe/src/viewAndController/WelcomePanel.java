package viewAndController;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**The WelcomePanel is the first panel the user sees. 
 * It explains the purpose of the test and asks for the users name. 
 * If the user presses the start-button the class IsMoMeTest is called.
 */
public class WelcomePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//variables
	private JTextArea welcomeText;
	private JLabel welcome, userName, logo;
	private JTextField name;
	private JButton close, start;
	private TheWelcomeListener lis = new TheWelcomeListener();
	public static String userNameString;
	public static JFrame miniFrame;
	public static int error=0;

	//constructor
	public WelcomePanel() {
		//set layout to "null" to be able to position components with .setBounds()
		setLayout(null);

		//set Background of Panel
		setBackground(Color.white);

		//create & add exit-button
		close = new JButton("Exit");
		close.setBounds(820,15,80,40);
		add(close);
		close.addActionListener(lis);	

		//create & add headline
		welcome= new JLabel("Willkommen bei");
		welcome.setFont(new Font ("Times New Roman", Font.BOLD, 24));	
		welcome.setBounds(350,30,300,40);
		add(welcome);	

		//create & add logo
		Icon pic = new ImageIcon(getClass().getResource("LOGO.png"));
		logo = new JLabel(pic);
		logo.setBounds(190,80,550,150);
		add(logo);

		//create & add welcome text
		welcomeText = new JTextArea();
		welcomeText.setLineWrap(true);
		welcomeText.setWrapStyleWord(true);			
		welcomeText.setFont(new Font ("Times New Roman", Font.PLAIN, 15));
		welcomeText.setForeground(Color.DARK_GRAY);
		welcomeText.setText("Machst du bald deinen Abschluss und bist auf der Suche nach dem passenden Beruf "
				+ "für dich? Oder orientierst du dich beruflich gerade um? Dann bist du hier ganz richtig! "
				+ "Teste dich selbst mit dem IsMoMe-Berufsselbsttest und lerne dabei viele spannende, "
				+ "neuartige und ungewöhnliche Berufe kennen, die durch die Digitalisierung entstanden sind!");
		welcomeText.setBounds(190, 270, 600, 100);
		welcomeText.setEditable(false);
		add(welcomeText);

		//create & add text and text-field for user name
		userName = new JLabel("Gib hier deinen Namen ein: ");	
		userName.setFont(new Font ("Times New Roman", Font.PLAIN, 20));
		userName.setBounds(225, 380, 300, 100);
		add(userName);

		name = new JTextField(15);
		name.setBounds(528, 415, 200, 30);
		add(name);	
		name.addActionListener(lis);

		//create & add start-button
		start = new JButton("Start");
		start.setBounds(420, 500, 80, 30);
		add(start);
		start.addActionListener(lis);
	}


	/**TheWelcomeListener is the ActionListener for the WelcomePanel.
	 * It reacts to the selection of the start- and close-buttons 
	 * and saves the userName
	 */
	private class TheWelcomeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==start) {
				userNameString=name.getText();
				if (userNameString.equals("")) {		//if no name is typed in, open new frame with the panel to tell user
					error = 1;
					miniFrame = new JFrame("Achtung!"); 
					miniFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					miniFrame.setSize(320,200);	
					miniFrame.add(new ErrorPanel());
					miniFrame.setResizable(false);
					miniFrame.setVisible(true); 
					miniFrame.setLocationRelativeTo(null);
				} else {
					error = 0;
					main.IsMoMeMain.cl.show(main.IsMoMeMain.panelCont, "2");
				}
			} else if(e.getSource()==close) {
				System.exit(0);
			}
		}
	}

}