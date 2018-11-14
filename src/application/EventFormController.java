package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;



public class EventFormController {
	@FXML private Button btnSubmit;
	@FXML private AnchorPane anchorPane;
	@FXML private TextField tfTitle;
	@FXML private TextField tfDesc;
	@FXML private TextField tfStartTime;
	@FXML private TextField tfEndTime;
	private Stage stage = new Stage();
	private Event event;
	
	public void initialize() {
		event = new Event();
		System.out.println("EventFromController Initialized");
	}
	
	public void setEvent() {
		event.setEventDesc(tfDesc.getText());
		event.setEventTitle(tfTitle.getText());
		event.setStartTime(Integer.parseInt(tfStartTime.getText()));
		event.setEndTime(Integer.parseInt(tfEndTime.getText()));
		System.out.println(event);
	}

	public Event getEvent() {
		Parent root;
		
		try {
			root = FXMLLoader.load(getClass().getResource("EventForm.fxml"));
			Scene scene = new Scene(root,300,300);
			stage.setScene(scene);
			stage.setWidth(400);
			stage.setHeight(300);
			stage.showAndWait();
			
			
			
			
			setEvent();
			
			System.out.println("Closing");
			if(event == null) {
				System.out.println(true);
			}
			return event;
		} catch (IOException e) {
			e.printStackTrace();
			if(event == null) {
				System.out.println(true);
			}
			return event;
		}
		
		
	}
	
	
}
