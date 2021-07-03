package models;

import java.io.*;
import java.util.*;

/**The class IsMoMeTest provides the question and answers for the program.
 */
public class IsMoMeTest {
	//variables
	public static int t=10;
	private String getQaA, numberS;
	private String [] QaA = new String[5];
	private Scanner fileScan = null, elementsScan = null;
		
	/**In the method "getQaA()" a question and the corresponding answers are read from a document in each round
	 */
	public String [] getQaA() {
		//a new scanner object "fileScan" is created on the document "frageliste.txt"
		try {
			fileScan = new Scanner(new File("C:\\Users\\frageliste.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, etwas ist beim Laden der Fragen falsch gelaufen!");
		}

		//document gets scanned
		while (fileScan.hasNext()) { 				
			getQaA = fileScan.nextLine(); 				
			numberS = String.valueOf(11-t);				//String "numberS" is getting initialized with (11-t), t counts backwards in QuestionPanel line 189
			if (getQaA.contains(numberS+".")) {			//if String "getQaA" contains "numberS"
				elementsScan = new Scanner(getQaA); 		
				elementsScan.useDelimiter(";");				
			} 
		}

		//for-loop with i<5, because one question and 4 answers should be saved
		for (int i = 0; i < 5; i++) {						
			QaA[i] = elementsScan.next();			//the 5 elements from the line 43 are saved in a String-Array					
		}
		elementsScan.close(); 							
		return QaA;									//String-Array "QaA" (initialized with 1 question & 4 answers) gets returned
	}
}