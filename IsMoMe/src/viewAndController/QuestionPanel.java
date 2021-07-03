package viewAndController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import dao.IsMoMeDao;
import models.IsMoMeTest;

/**The QuestionPanel is the main panel on which the user answers 10 questions 
 * and a job with the related information is presented during each question.
 */
public class QuestionPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	//variables
	private String q,a1,a2,a3,a4;
	public String answerForDB;
	public static ArrayList<String> safedAnswers= new ArrayList<String>();
	private JLabel job, suggestion, question, logo;
	private JButton next, close;
	private JRadioButton answer1, answer2, answer3, answer4;
	private JTextArea informationBox;
	private Border blueborder;								
	private JComboBox<String> jobDescription;
	public static JFrame miniFrame;
	TheQuestionListener lis = new TheQuestionListener();
	IsMoMeTest immt= new IsMoMeTest();

	//constructor
	public QuestionPanel() {	
		//set layout to "null" to be able to position components with .setBounds()
		setLayout(null);

		//set background of Panel
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

		//get the first question and answers from method "QaA" & initialize separate Strings with them (only for the first question)
		if(IsMoMeTest.t==10) {
			String [] QaA = immt.getQaA();
			q=QaA[0];						
			a1=QaA[1];
			a2=QaA[2];
			a3=QaA[3];
			a4=QaA[4];
		}

		//create & add question
		question = new JLabel(q);		
		question.setBounds(170, 250, 550, 40);
		add(question);					

		//create & add answer-buttons
		answer1= new JRadioButton(a1);
		answer1.setBackground(new Color(151,189,202));
		answer1.setBounds(170, 300, 360, 25);
		add(answer1);
		answer1.addActionListener(lis);	

		answer2= new JRadioButton(a2);
		answer2.setBackground(new Color(151,189,202));
		answer2.setBounds(170, 330, 360, 25);
		add(answer2);
		answer2.addActionListener(lis);

		answer3= new JRadioButton(a3);
		answer3.setBackground(new Color(151,189,202));
		answer3.setBounds(170, 360, 360, 25);
		add(answer3);
		answer3.addActionListener(lis);

		answer4= new JRadioButton(a4);
		answer4.setBackground(new Color(151,189,202));
		answer4.setBounds(170, 390, 360, 25);
		add(answer4);
		answer4.addActionListener(lis);

		//create & add next-button
		next = new JButton("Next");
		next.setBounds(580, 345, 140, 25);
		add(next);
		next.addActionListener(lis);

		//create & add (random) job suggestion
		IsMoMeDao.getJob();	

		suggestion= new JLabel("Vielleicht interessant:");
		suggestion.setFont(new Font ("Times New Roman", Font.BOLD, 15));		
		suggestion.setBounds(150, 480, 150, 25);
		add(suggestion);

		job= new JLabel(IsMoMeDao.jobNameQP);
		job.setFont(new Font ("Times New Roman", Font.BOLD, 14));	
		job.setBounds(150, 500, 380, 55);
		add(job);

		//create & add JComboBox to let user decide which information she/he wants to see about the (random) job		
		String descriptionComboBox[] = {"Wähle aus...","Beschreibung","Gehalt","Weitere Informationen"};
		jobDescription = new JComboBox<String>(descriptionComboBox);
		jobDescription.setBounds(150, 540, 200, 25);
		add(jobDescription);
		jobDescription.addActionListener(lis);

		//create field where from user desired information appears
		Color col= new Color(151,189,202);							
		blueborder = BorderFactory.createLineBorder(col, 4);			

		informationBox = new JTextArea();
		informationBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		informationBox.setBounds(500, 480, 300, 100);
		informationBox.setLineWrap(true);	
		informationBox.setWrapStyleWord(true);	
		informationBox.setForeground(Color.black);
		informationBox.setBorder(blueborder);
		informationBox.setText("...was macht man in diesem Beruf?");
		add(informationBox);
	} 

	private void nextQuestion() {
		if (answerForDB==null) {				//...if no answer is selected, open new frame with the panel to tell user
			miniFrame = new JFrame("Achtung!"); 
			miniFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			miniFrame.setSize(320,200);	
			miniFrame.add(new ErrorPanel());
			miniFrame.setResizable(false);
			miniFrame.setVisible(true); 
			miniFrame.setLocationRelativeTo(null);	
		} else {								//...else safe the selected answer in ArrayList for final job-proposals
			safedAnswers.add(answerForDB);		
			answerForDB = null;
			IsMoMeTest.t = IsMoMeTest.t-1;			//reduce t by 1 for the next question

			if (IsMoMeTest.t==0) {					//if the 10th question is over call class IsMoMeTest()
				IsMoMeDao.selectionDB();
				viewAndController.ByePanel bp = new viewAndController.ByePanel();	
				main.IsMoMeMain.panelCont.add(bp, "3");			// add ByePanel to panelCont from CardLayout	
				main.IsMoMeMain.cl.show(main.IsMoMeMain.panelCont, "3");
			} else {								//else update all information for QuestionPanel
				new QuestionPanel();

				answer1.setSelected(false);				//set all answer-buttons to "not selected"
				answer2.setSelected(false);
				answer3.setSelected(false);
				answer4.setSelected(false);

				if (IsMoMeTest.t > 0)	{				//get & set next question and answers
					String [] QaA = immt.getQaA();
					q=QaA[0];
					a1=QaA[1];
					a2=QaA[2];
					a3=QaA[3];
					a4=QaA[4];
					question.setText(q);
					answer1.setText(a1);
					answer2.setText(a2);
					answer3.setText(a3);
					answer4.setText(a4);
				}
				IsMoMeDao.getJob();						//get next random job
				job.setText(IsMoMeDao.jobNameQP);
				informationBox.setText("...was macht man in diesem Beruf?");
			}
			if (IsMoMeTest.t<6) {				//from the 5th question on, show only the two possible answers
				answer2.setVisible(false);
				answer4.setVisible(false);
			}
		}
	}

	/**TheQuestionListener is the ActionListener for the QuestionPanel.
	 * It reacts to the selection of the answer-buttons, the next- and close-buttons 
	 * and the JComboBox for showing job information.
	 */
	private class TheQuestionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==close) {					//if "Exit" is selected, the program closes
				System.exit(0);
			}else if(e.getSource()==answer1) {			//if "answer1" is selected, indicate that it is selected
				answer1.setSelected(true);
				answer2.setSelected(false);
				answer3.setSelected(false);
				answer4.setSelected(false);
				answerForDB = "a";	
			}else if (e.getSource()==answer2) {			//if "answer2" is selected, indicate that it is selected
				answer1.setSelected(false);
				answer2.setSelected(true);
				answer3.setSelected(false);
				answer4.setSelected(false);
				answerForDB = "b";			
			}else if (e.getSource()==answer3) {			//if "answer3" is selected, indicate that it is selected
				answer1.setSelected(false);
				answer2.setSelected(false);
				answer3.setSelected(true);
				answer4.setSelected(false);
				answerForDB = "c";				
			}else if (e.getSource()==answer4) {			//if "answer4" is selected, indicate that it is selected
				answer1.setSelected(false);
				answer2.setSelected(false);
				answer3.setSelected(false);
				answer4.setSelected(true);
				answerForDB = "d";
			}else if (e.getSource()==jobDescription) {	//if a option is selected in the JComboBox, display the desired information
				if (jobDescription.getSelectedItem().equals("Beschreibung")) {
					informationBox.setText(IsMoMeDao.jobDescriptionQP);
				}else if (jobDescription.getSelectedItem().equals("Gehalt")) {
					informationBox.setText(IsMoMeDao.jobSalaryQP);
				}else if (jobDescription.getSelectedItem().equals("Weitere Informationen")) {
					informationBox.setText("Weitere Infos findest du hier: "+IsMoMeDao.jobLinkQP);
				}else {
					informationBox.setText("...was macht man in diesem Beruf?");
				}
			}else if (e.getSource()==next) {			//If "next" is selected...
				nextQuestion();

			}

		}

	}
	
	
}