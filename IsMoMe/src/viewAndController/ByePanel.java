package viewAndController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import dao.IsMoMeDao;

/**The ByePanel is the last window the user sees.
 * It shows the final job information that matches the user's answers from the test.
 */
public class ByePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//variables
	String descriptionComboBox1[] = new String[500];
	String descriptionComboBox2[] = new String[500];
	String descriptionComboBox3[] = new String[500];
	private JComboBox<String> jobDescription1, jobDescription2, jobDescription3;
	private JLabel logo, resultText, match1, match2, match3, job1, job2, job3;
	private JTextArea info1, info2, info3;
	private JButton close;
	private Border blueborder;
	TheByeListener lis = new TheByeListener();
	Color color = new Color(74,112,139);		

	//constructor
	public ByePanel() {
		//set layout to "null" to be able to position components with .setBounds()
		setLayout(null);

		//set Background color of Panel
		setBackground(Color.white);

		//create & add exit-button
		close = new JButton("Exit");
		close.setBounds(820,15,80,40);
		add(close);
		close.addActionListener(lis);	

		//create & add logo
		Icon pic = new ImageIcon(getClass().getResource("LOGO.png"));
		logo = new JLabel(pic);
		logo.setBounds(190,80,550,150);
		add(logo);

		//create & add JLabels with final, personalized job suggestions
		resultText= new JLabel("Diese Jobs passen zu dir " + viewAndController.WelcomePanel.userNameString + ":");
		resultText.setFont(new Font ("Times New Roman", Font.BOLD, 16));	
		resultText.setBounds(370, 300, 300, 40);
		add(resultText);	

		//create & add JLabels & JComboBoxes so user can see (all) information on final, personalized job suggestions
		Color col= new Color(151,189,202);
		blueborder = BorderFactory.createLineBorder(col, 4);

		//1st job-suggestion 
		job1= new JLabel(IsMoMeDao.jobName[0]);
		job1.setFont(new Font ("Times New Roman", Font.BOLD, 14));
		job1.setBounds(50, 350, 380, 40);
		add(job1);

		match1= new JLabel("erfüllt "+String.valueOf(IsMoMeDao.jobMatch[0])+"/10 Kriterien");
		match1.setFont(new Font ("Times New Roman", Font.BOLD, 14));
		match1.setForeground(color);
		match1.setBounds(50, 370, 380, 40);
		add(match1);

		String descriptionComboBox1[] = {"Wähle aus...","Beschreibung","Gehalt","Weitere Informationen"};
		jobDescription1 = new JComboBox<String>(descriptionComboBox1);
		jobDescription1.setBounds(50, 400, 200, 40);
		add(jobDescription1);
		jobDescription1.addActionListener(lis);

		info1 = new JTextArea();
		info1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		info1.setBounds(50, 450, 200, 150);
		info1.setLineWrap(true);
		info1.setWrapStyleWord(true);
		info1.setText("...was macht man in diesem Beruf?");
		info1.setForeground(Color.black);
		info1.setBorder(blueborder);
		add(info1);

		//2nd job-suggestion
		job2 = new JLabel(IsMoMeDao.jobName[1]);
		job2.setFont(new Font ("Times New Roman", Font.BOLD, 14));	
		job2.setBounds(350, 350, 380, 40);
		add(job2);

		match2= new JLabel("erfüllt "+String.valueOf(IsMoMeDao.jobMatch[1])+"/10 Kriterien");
		match2.setFont(new Font ("Times New Roman", Font.BOLD, 14));
		match2.setForeground(color);
		match2.setBounds(350, 370, 380, 40);
		add(match2);

		String descriptionComboBox2[] = {"Wähle aus...","Beschreibung","Gehalt","Weitere Informationen"};
		jobDescription2 = new JComboBox<String>(descriptionComboBox2);
		jobDescription2.setBounds(350, 400, 200, 40);
		add(jobDescription2);
		jobDescription2.addActionListener(lis);

		info2 = new JTextArea();
		info2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		info2.setBounds(350, 450, 200, 150);
		info2.setLineWrap(true);
		info2.setWrapStyleWord(true);
		info2.setText("...was macht man in diesem Beruf?");
		info2.setForeground(Color.black);
		info2.setBorder(blueborder);
		add(info2);

		//3rd job-suggestion
		job3 = new JLabel(IsMoMeDao.jobName[2]);
		job3.setFont(new Font ("Times New Roman", Font.BOLD, 14));	
		job3.setBounds(650, 350, 380, 40);
		add(job3);

		match3= new JLabel("erfüllt "+String.valueOf(IsMoMeDao.jobMatch[2])+"/10 Kriterien");
		match3.setFont(new Font ("Times New Roman", Font.BOLD, 14));
		match3.setForeground(color);
		match3.setBounds(650, 370, 380, 40);
		add(match3);

		String descriptionComboBox3[] = {"Wähle aus...","Beschreibung","Gehalt","Weitere Informationen"};
		jobDescription3 = new JComboBox<String>(descriptionComboBox3);
		jobDescription3.setBounds(650, 400, 200, 40);
		add(jobDescription3);
		jobDescription3.addActionListener(lis);

		info3 = new JTextArea();
		info3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		info3.setBounds(650, 450, 200, 150);
		info3.setLineWrap(true);
		info3.setWrapStyleWord(true);
		info3.setText("...was macht man in diesem Beruf?");
		info3.setForeground(Color.black);
		info3.setBorder(blueborder);
		add(info3);
	}


	/**TheByeListener is the ActionListener for the ByePanel.
	 * It reacts to the selection of the close-button and the JComboBoxes for showing job information.
	 */
	private class TheByeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==close) {
				System.exit(0);
			}else if(e.getSource()==jobDescription1){
				if (jobDescription1.getSelectedItem().equals("Beschreibung")) {
					info1.setText(IsMoMeDao.jobDescription[0]);
				}else if (jobDescription1.getSelectedItem().equals("Gehalt")) {
					info1.setText(IsMoMeDao.jobSalary[0]);
				}else if (jobDescription1.getSelectedItem().equals("Wähle aus...")) {
					info1.setText("...was macht man in diesem Beruf?");
				}else if (jobDescription1.getSelectedItem().equals("Weitere Informationen")) {
					info1.setText("Weitere Infos findest du hier: "+IsMoMeDao.jobLink[0]);
				}
			}else if(e.getSource()==jobDescription2){
				if (jobDescription2.getSelectedItem().equals("Beschreibung")) {
					info2.setText(IsMoMeDao.jobDescription[1]);
				}else if (jobDescription2.getSelectedItem().equals("Gehalt")) {
					info2.setText(IsMoMeDao.jobSalary[1]);
				}else if (jobDescription2.getSelectedItem().equals("Wähle aus...")) {
					info2.setText("...was macht man in diesem Beruf?");
				}else if (jobDescription2.getSelectedItem().equals("Weitere Informationen")) {
					info2.setText("Weitere Infos findest du hier: "+IsMoMeDao.jobLink[1]);
				}
			}else if(e.getSource()==jobDescription3) {
				if (jobDescription3.getSelectedItem().equals("Beschreibung")) {
					info3.setText(IsMoMeDao.jobDescription[2]);
				}else if (jobDescription3.getSelectedItem().equals("Gehalt")) {
					info3.setText(IsMoMeDao.jobSalary[2]);
				}else if (jobDescription3.getSelectedItem().equals("Wähle aus...")) {
					info3.setText("...was macht man in diesem Beruf?");
				}else if (jobDescription3.getSelectedItem().equals("Weitere Informationen")) {
					info3.setText("Weitere Infos findest du hier: "+IsMoMeDao.jobLink[2]);
				}
			}
		}
	}

}