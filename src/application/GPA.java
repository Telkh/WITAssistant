package application;

public class GPA {
	private final String [] grade = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"}; //grades
	private double [] weight = new double[grade.length]; //weight of letter grades
	private String course;
	private String earnedGrade;
	private int credits;
	
	 GPA(String course, String earnedGrade, int credits) {
		 generateValue();
		 this.course = course;
		 this.earnedGrade = earnedGrade;
		 this.credits = credits;
		 
	 }
	 
	 GPA(){
			generateValue(); 
		 }
		 
	 
	 public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	
	 public String getCourse(){
		 return course;
	 }//
	 
	
	public String getEarnedGrade() {
		return earnedGrade;
	}

	public void setEarnedGrade(String earnedGrade) {
		this.earnedGrade = earnedGrade;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	/*
	 * Generates the weights of each letter grade
	 */
	private void generateValue() {
		weight[0] = 4.00;
		double diff = 0.3333;
		for (int i = 1; i < weight.length; i++) {
			weight[i] = weight[i-1] - diff; 
		}
	}
	/*
	 * Finds the total points earned in the course
	 */
	public double getEarned(int numCredits, String grade) {
		double points = numCredits * getWeight(grade);
		return points;
	}
	
	/*
	 * Finds the weight of the letter grade.
	 */
	public double getWeight(String gradeInput) {
		int i = 0;
		while(gradeInput.equalsIgnoreCase(grade[i])) {
			i++;
		}
		return weight[i];
	}
	
	public String toString() {
		System.out.printf("The grade for %s is %s with = weight %f, points %f", getWeight("A+"), getEarned(4,  "A+"));
		
		return "";
	}
	
}
