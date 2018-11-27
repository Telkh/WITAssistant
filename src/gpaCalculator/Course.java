package gpaCalculator;

public class Course extends GPA {
	private String course;
	private String grade;
	private int credits;
	
	public Course(String course, String grade, int credits) {
		 generateValue();
		 this.course = course;
		 this.grade = grade;
		 this.credits = credits;
		 
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
}
