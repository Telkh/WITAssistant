package gpaCalculator;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ClassRow extends HBox{
	
	private TextField tfCourseName;
	private ComboBox gradeOptions;
	private TextField tfCredits;
	private final String [] GRADE = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"}; //grades

	
	
	public ClassRow() {
		tfCourseName= new TextField();
		tfCredits = new TextField();
		gradeOptions = new ComboBox();
		
		tfCourseName.setPromptText("Course Name");
		tfCredits.setPromptText("Credits");
		gradeOptions.promptTextProperty().set("Grade");
		for(int i = 0; i < GRADE.length; i++) {
			gradeOptions.getItems().add(GRADE[i]);
		}
		super.getChildren().addAll(tfCourseName, gradeOptions, tfCredits);
	}
	
}
