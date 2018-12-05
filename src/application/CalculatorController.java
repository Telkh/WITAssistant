package application;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import csvHandler.CsvHandler;
import gpaCalculator.ClassRow;
import gpaCalculator.GPA;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    CsvHandler csv = new CsvHandler("gpaData");
    public void initialize() {
    	
    	// Add six ClassRow objects to GUI
    	for(int i = 0; i < 6; i++) {
    		formList.add(new ClassRow());
    		vBoxForm.getChildren().add(formList.get(i));;
    	}
    	
    	
    	
    	// Checks to see if each ClassRow has data entered. If data exists, then use it to calculate GPA
    	btnSubmit.setOnMouseClicked(e -> {
    		ArrayList<GPA> course = new ArrayList<GPA>();
	    	for(int j = 0; j < formList.size(); j++) {
	    		ClassRow temp = formList.get(j);
	    		if(temp.isFilled()) {
	    			course.add(new GPA(temp.getName(), temp.getGrade(), temp.getCredits()));
	    			
	    			System.out.println(temp + " heloo");
	    			
	    		}
	    	}
	    	csv.GPAwriter(course);
	    	System.out.println(GPA.getAverage(course));
	    	String gpa1 = String.format("%.2f", GPA.getAverage(course));
	    	
	    	gpa.setText(gpa1);
	    	System.out.println(gpa1);
    	});
    
    }

    public void addToDB(ArrayList<GPA> loaded){
    	
    	
    }
}
