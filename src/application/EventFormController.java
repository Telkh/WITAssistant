package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventFormController {
	@FXML private Button btnSubmit;
	@FXML private AnchorPane anchorPane;
	@FXML private TextField tfTitle;
	@FXML private TextField tfDesc;
	@FXML private TextField tfStartTime;
	// This is a test comment
	private Event event;
	
	public void initialize() {
		
	}
	
	public void setEvent() {
		
	}
	
	public Event getEvent() {
		Stage stage = new Stage();
		Parent root;
		Event newEvent = new Event("","",0,0);
		try {
			root = FXMLLoader.load(getClass().getResource("EventForm.fxml"));
			Scene scene = new Scene(root,300,300);
			stage.setScene(scene);
			stage.setWidth(400);
			stage.setHeight(300);
			stage.showAndWait();
			setEvent();
			return newEvent;
			
		} catch (IOException e) {
			e.printStackTrace();
			return newEvent;
		}
	}
	
	
}
