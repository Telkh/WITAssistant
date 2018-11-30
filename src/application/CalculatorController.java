package application;

import gpaCalculator.ClassRow;
import javafx.fxml.FXML;
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
    
    public void initialize() {
    	
    	for(int i = 0; i < 6; i++) {
    		vBoxForm.getChildren().add(new ClassRow());
    	}
    }

}
