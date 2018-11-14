package application;

public class GPA {
//gp]a Class
	final static String [] grade = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"};
	static double [] weight = new double[grade.length];
	
	public static void generateValue() {
		weight[0] = 4.00;
		double diff = 0.3333;
		for (int i = 1; i < weight.length; i++) {
			weight[i] = weight[i-1] - diff; 
		}
	}
	public static double getEarned(int numCredits, String grade) {
		double points = numCredits * getWeight(grade);
		return points;
	}
	
	public static double getWeight(String gradeInput) {
		int i = 0;
		while(gradeInput.equalsIgnoreCase(grade[i])) {
			i++;
		}
		return weight[i];
	}
	
	public String toString() {
		System.out.printf("grade A+ credits 4 = weight %f, points %f", getWeight("A+"), getEarned(4,  "A+"));
		
		return "";
	}
	
}
