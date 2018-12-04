package gpaCalculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ClassRow extends HBox{
	//TEst
	private TextField tfCourseName;
	private ComboBox gradeOptions;
	private TextField tfCredits;
	private final String [] GRADE = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"}; //grades
	private boolean [] isFilled = new boolean[3];
	GPA gpa = new GPA();
	
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
		super.setAlignment(Pos.CENTER);
		super.setSpacing(10);
		setProperties();
	}
	
	private void setProperties() {
		tfCourseName.textProperty().addListener((obs, oldval, newval) -> {
			isFilled[0] = true;
		});
		
		gradeOptions.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			if(newval != null) {
				isFilled[1] = true;
			}
		});
		
		tfCredits.textProperty().addListener((obs, oldval, newval) -> {
			isFilled[2] = true;
		});
	}
	
	public boolean isFilled() {
		for(int i = 0; i < isFilled.length; i++) {
			if(!isFilled[i]) {
				return false;
			}
		}
		return true;
	}
	
	public String getName() {
		if(isFilled[0]) {
			return tfCourseName.getText();
		}
		return null;
	}
	
	public String getGrade() {
		if(isFilled[1]) {
			return (String) gradeOptions.getSelectionModel().getSelectedItem();
		}
		return null;
	}
	
	public int getCredits() {
		if(isFilled[2]) {
			return Integer.parseInt(tfCredits.getText());
		}
		return -1;
	}
	
	public String toString() {
		return(getName() + " " + getGrade() + " " + getCredits());
	}
}
