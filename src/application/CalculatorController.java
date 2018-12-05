package application;

import java.util.ArrayList;
import csvHandler.CsvHandler;
import gpaCalculator.ClassRow;
import gpaCalculator.GPA;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CalculatorController {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private HBox topSection;
	@FXML
	private Label lblTitle;
	@FXML
	private VBox vBoxForm;
	@FXML
	private HBox lowerSection;
	@FXML
	private Button btnSubmit;
	@FXML
	private TextField gpa;
	private ArrayList<ClassRow> formList = new ArrayList<ClassRow>();
	
	private ArrayList<GPA> course = new ArrayList<GPA>(); //stores the gpa elements entered

	public void initialize() {

		// Add six ClassRow objects to GUI
		for (int i = 0; i < 6; i++) {
			formList.add(new ClassRow());
			vBoxForm.getChildren().add(formList.get(i));
			;
		}

		// Checks to see if each ClassRow has data entered. If data exists, then use it
		// to calculate GPA
		//Submit bottom
		btnSubmit.setOnMouseClicked(e -> {
			for (int j = 0; j < formList.size(); j++) {
				ClassRow temp = formList.get(j);
				if (temp.isFilled()) { //check if row is filled
					course.add(new GPA(temp.getName(), temp.getGrade(), temp.getCredits())); //add each row arrayList
					//System.out.println("Submit buttom debug: " + temp); //debug

				}
			}
			//Generates average GPA
			String gpa1 = String.format("%.2f", GPA.getAverage(course));//
			gpa.setText(gpa1);
			//System.out.println("Submit GPA debug: " + gpa1); //debug
			addToDB();
			
		});

	}
	/**
	 * Add GPA elements to CSV file
	 */
	public void addToDB() {
		CsvHandler csv = new CsvHandler("gpaData"); //Initializes CSV object. Use file gpaData
		csv.GPAwriter(course);//saves GPA data to file
		
	}
}
