package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ds.Database;
import models.IsMoMeTest;

/** the IsMoMeDao class contains all methods that access the database 
 */
public class IsMoMeDao {
	private static int randomJobNumber;
	private static ResultSet rs, rsQP;
	private static Connection con;
	private static Database db;
	private static Statement stmt;
	private static int [] randomNumberArray = new int [11];
	private static boolean isDuplicate;
	public static String jobDescriptionQP, jobLinkQP, jobSalaryQP, jobNameQP, sql;
	public static String[] jobName = new String[300];
	public static String[] jobSalary = new String[300];
	public static String[] jobDescription = new String[300];
	public static String[] jobLink = new String[300];
	public static int[] jobMatch = new int[300];

	/**In the method "getJob()" the SQL statement selects the jobs that matches with the random number that is generated in this method. 
	 * The salary, description and link of the job are also selected. The random number gets generated and checked if it is duplicate 
	 * (and if it is, the number gets generated again, until no number is duplicate).
	 */
	public static void getJob() {
		db = Database.getInstance();	
		
		//two for-loops to create random numbers that jobs are not duplicate
		if (IsMoMeTest.t==10) {
			for (int i = 0; i < 11; i++) {
				do {
				isDuplicate = false;
				randomJobNumber = (int) (Math.random()*46+1);
					for (int j=0; j < i; j++) {
						if (randomJobNumber == randomNumberArray[j]) { 		//checks if number is duplicate
						isDuplicate = true;
						}
					}
				} while (isDuplicate);
				randomNumberArray[i] = randomJobNumber;
			}
		}
		
		//database gets connected and searched for the random number and returns the job with the job_ID from database
		try {
			con = db.getConnection();
			stmt = con.createStatement();

			sql = "SELECT j.JOB_NAME AS NAME, j.JOB_SALARY AS SALARY, j.JOB_DESCRIPTION AS DESCRIPTION, j.JOB_LINK AS LINK FROM JOBS j, "
					+ "ABILITIES a WHERE j.JOB_ID = a.JOB_ID AND j.JOB_ID = " + randomNumberArray[10-IsMoMeTest.t] + "";
			rsQP = stmt.executeQuery(sql);
			while(rsQP.next()){
				jobNameQP = rsQP.getString("NAME");		// Strings are initialized with information from database 
				jobSalaryQP = rsQP.getString("SALARY");
				jobDescriptionQP = rsQP.getString("DESCRIPTION");
				jobLinkQP = rsQP.getString("LINK");
			}
		} catch (SQLException e) {}
	}
	
	/**In the method "SelectionDB()" the SQL statement selects the jobs that matches with the answers from the user. 
	 * The salary, description and link of the job are also selected. The jobs are ordered by the number of matches and saved in arrays.
	 */
	public static void selectionDB () {
		//variables 
		db = Database.getInstance();	
		int counter=0;

		//database gets connected and searched for ArrayList "safedAnswers"
		try {
			con = db.getConnection();
			stmt = con.createStatement();

			sql = "SELECT j.JOB_NAME AS NAME, COUNT(1) AS COUNTNUMBER, j.JOB_SALARY AS SALARY, j.JOB_DESCRIPTION AS DESCRIPTION, j.JOB_LINK AS LINK FROM JOBS j, "
					+ "ABILITIES a WHERE j.JOB_ID = a.JOB_ID AND (";
			for (int i = 1; i <= 10; i++) {			// loop to select the answer from every question
				if (i<10) {
					sql = sql + "(a.QUESTION = "+ i +" AND a.ANSWER = \'"+ viewAndController.QuestionPanel.safedAnswers.get(i-1) +"\') OR";	//looks for the answer of every question and i tell which question
				} else {
					sql = sql + "(a.QUESTION = "+ i +" AND a.ANSWER = \'"+ viewAndController.QuestionPanel.safedAnswers.get(i-1) +"\')";		// in the SQL statement of the last question should be not OR at the end
				}
			}
			sql = sql + ") GROUP BY j.JOB_NAME, j.JOB_SALARY, j.JOB_DESCRIPTION, j.JOB_LINK ORDER BY 2 DESC";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				jobName[counter] = rs.getString("NAME");		// Arrays are initialized with information from database
				jobSalary[counter] = rs.getString("SALARY");
				jobDescription[counter] = rs.getString("DESCRIPTION");
				jobLink[counter] = rs.getString("LINK");
				jobMatch[counter] = rs.getInt("COUNTNUMBER");
				counter++;				// the counter increments that the information from every job are saved at the next position of the Array
			}
		} catch (SQLException e) {}
	}
}