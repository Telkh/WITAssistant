package application;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import gpaCalculator.ClassRow;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private ArrayList<ClassRow> formList = new ArrayList<ClassRow>();
    public void initialize() {
    	
    	// Add six ClassRow objects to GUI
    	for(int i = 0; i < 6; i++) {
    		formList.add(new ClassRow());
    		vBoxForm.getChildren().add(formList.get(i));;
    	}
    	
    	// Checks to see if each ClassRow has data entered. If data exists, then use it to calculate GPA
    	btnSubmit.setOnMouseClicked(e -> {
	    	for(int j = 0; j < formList.size(); j++) {
	    		ClassRow temp = formList.get(j);
	    		if(temp.isFilled()) {
	    			System.out.println(temp);
	    		}
	    	}
    	});
    }

}
