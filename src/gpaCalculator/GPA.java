
package gpaCalculator;

import java.util.ArrayList;

/**
 * This class calculates the GPA of course. The maximum letter grade is A.
 * 
 * @author Jesus Esgueva esguevaj@wit.edu
 *
 */
public class GPA {
	private final String[] GRADE = { "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F" }; // grades
	private double[] weight = {4, 3.67, 3.33, 3, 2.67, 2.33, 2, 1.67, 1.33, 1, 0};//new double[GRADE.length]; // weight of letter grades
	private String course;
	private String grade;
	private int credits;

	public GPA(String course, String grade, int credits) {
		//generateValue();
		this.course = course;
		this.grade = grade;
		this.credits = credits;

	}

	// Getters and setters
	public GPA() {
		//generateValue();
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

//Test
	/*
	 * Generates the weights of each letter grade
	 */
	void generateValue() {
		weight[0] = 4.00; // Maximum points
		final double diff = 0.3333; // difference between grades
		for (int i = 1; i < weight.length; i++) {
			weight[i] = weight[i - 1] - diff;
		}
	}

	/**
	 * Finds the total points earned in the course
	 * 
	 * @return Total points obtained
	 */
	public double getPoints() {
		double points = this.credits * getWeight(this.grade); // number of credits times the weight of the grade.
		return points;
	}

	/**
	 * Finds the weight of the letter grade.
	 * 
	 * @param Grade obtained in the course
	 * @return The weight of the grade
	 */
	public double getWeight(String gradeInput) {
		int i = 0;
		while (!gradeInput.equalsIgnoreCase(GRADE[i])) {
			i++;
		}
		return weight[i];
	}

	/**
	 * 
	 * @param courses - ArrayList with GPA objects
	 * @return total number of credits
	 */
	public static int sumCredits(ArrayList<GPA> courses) {
		int sum = 0;
		for (int i = 0; i < courses.size(); i++) {
			sum += courses.get(i).credits;
		}
		return sum;
	}

	/**
	 * 
	 * @param courses - ArrayList with GPA objects
	 * @return Average GPA
	 */
	public static double getAverage(ArrayList<GPA> courses) {
		double sum = 0;
		for (int i = 0; i < courses.size(); i++) {
			sum += courses.get(i).getPoints();
		}
		double average = sum / sumCredits(courses); // average points / sum of credits.
		return average;
	}
	
	

}
