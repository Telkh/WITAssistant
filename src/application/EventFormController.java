package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EventFormController extends Application{
	@FXML private Button btnSubmit;
	@FXML private AnchorPane anchorPane;
	@FXML private TextField tfTitle;
	@FXML private TextField tfDesc;
	@FXML private TextField tfStartTime;
	@FXML private TextField tfEndTime;
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Parent root
		
	}
}
