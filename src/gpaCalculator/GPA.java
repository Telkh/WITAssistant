package gpaCalculator;

import java.util.ArrayList;

public class GPA {
	private final String [] GRADE = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"}; //grades
	private double [] weight = new double[GRADE.length]; //weight of letter grades
	private String course;
	private String grade;
	private int credits;
	
	public GPA(String course, String grade, int credits) {
		 generateValue();
		 this.course = course;
		 this.grade = grade;
		 this.credits = credits;
		 //this.getPoints(this.credits, this.grade);
		 
	 }
	
	public GPA() {
		generateValue();
	}
	
	 public String getGrade() {
			return grade;
		}


		public void setGrade(String grade) {
			this.grade = grade;
		}


		public int getCredits() {
			return credits;
		}

		public void setCredits(int credits) {
			this.credits = credits;
		}

		
		 public String getCourse(){
			 return course;
		 }
		 

		public void setCourse(String course) {
			this.course = course;
		}

	/*
	 * Generates the weights of each letter grade
	 */
	void generateValue() {
		weight[0] = 4.00; //Maximum points
		final double diff = 0.3333; //difference between grades
		for (int i = 1; i < weight.length; i++) {
			weight[i] = weight[i-1] - diff;
		}
	}
	/*
	 * Finds the total points earned in the course
	 */
	public double getPoints() {
		double points = this.credits * getWeight(this.grade);
		return points;
	}
	
	/*
	 * Finds the weight of the letter grade.
	 */
	public double getWeight(String gradeInput) {
		int i = 0;
		while(!gradeInput.equalsIgnoreCase(GRADE[i])) {
			i++;
		}
		return weight[i];
	}
	public static int sumCredits(ArrayList<GPA> courses) {
		int sum = 0;
		for (int i = 0; i < courses.size(); i++) {
			sum += courses.get(i).credits;
		}
		return sum;
	}
	public static double getAverage(ArrayList<GPA> courses) {
		double sum = 0;
		for (int i = 0; i < courses.size(); i++) {
			sum += courses.get(i).getPoints();
		}
		double average = sum/sumCredits(courses); //average points / sum of credits.
		return average;
	}
	
}
