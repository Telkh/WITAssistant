package application;

import java.io.IOException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class EventFormController {
	@FXML private AnchorPane anchorPane;
	@FXML private Label lblPrompt;
    @FXML private Label lblTitle;
    @FXML private Label lblDesc;
    @FXML private Label lblStartTime;
    @FXML private Label lblEndTime;
    @FXML private TextField tfTitle;
    @FXML private TextField tfDesc;
    @FXML private ComboBox<String> startHour;
    @FXML private ComboBox<String> startMin;
    @FXML private ComboBox<String> endHour;
    @FXML private ComboBox<String> endMin;
    @FXML private HBox bottomSection;
    @FXML private Button btnCancel;
    @FXML private Button btnSubmit;
    @FXML private Label lblWarning;
    
	private Stage stage = new Stage();
	private Event event = new Event();
	private boolean [] isFilled = new boolean[6];
	
	public void initialize() {
		anchorPane.disabledProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
			}
		});
		
		System.out.println("EventFromController Initialized");
		lblPrompt.setText("Add Event for " + EventTime.getDate());
		lblWarning.setVisible(false);
		for(int i = 1; i <= 24; i++) {
			startHour.getItems().add(Integer.toString(i));
			endHour.getItems().add(Integer.toString(i));
		}
		
		for(int j = 0; j <= 59; j++) {
			if(j <= 9) {
				startMin.getItems().add("0" + j);
				endMin.getItems().add("0" + j);
			}
			else {
				startMin.getItems().add(Integer.toString(j));
				endMin.getItems().add(Integer.toString(j));
			}
		}
		setEvent();
	}

	public void setEvent() {
		tfTitle.textProperty().addListener((obs, oldval, newval) -> {
			isFilled[0] = true;
			event.setEventTitle(newval);
		});
		
		tfDesc.textProperty().addListener((obs, oldval, newval) -> {
			isFilled[1] = true;
			event.setEventDesc(newval);
		});
		
		startHour.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			isFilled[2] = true;
			event.getStartTime().setHour(newval);
		});
		
		startMin.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			isFilled[3] = true;
			event.getStartTime().setMinutes(newval);
		});
		
		endHour.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			isFilled[4] = true;
			event.getEndTime().setHour(newval);
		});
		
		endMin.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			isFilled[5] = true;
			event.getEndTime().setMinutes(newval);
		});
	}
	
	public void addToDB() {
		if(allFieldsFilled()) {
			System.out.println("Adding to DB");
			EventDB.addToEventDB(event, EventTime.getDate());
			System.out.println(EventDB.getListAt(EventTime.getDate()));
			clearInput();
		}
		else {
			lblWarning.setVisible(true);
		}
	}
	
	private boolean allFieldsFilled() {
		for(int i = 0; i < isFilled.length; i++) {
			if(!isFilled[i]) {
				return false;
			}
		}
		return true;
	}

	public Event getEvent() {
		return event;
	}
	
	public void resumeInput() {
		lblPrompt.setText("Add Event for " + EventTime.getDate());
	}
	
	public void pauseInput() {
		System.out.println("Pausing");
		lblPrompt.setText(lblPrompt.getText() + ": Paused");
	}
	
	public void clearInput() {

		//cancelRequested = true;
		if(!allFieldsFilled()) {
			lblWarning.setVisible(false);	
		}
		//anchorPane.getParent().setDisable(true);
	
	}
	
	public void cancel() {
		clearInput();
		
		if(!allFieldsFilled()) {
			lblWarning.setVisible(false);	
		}
		tfTitle.clear();
		tfDesc.clear();
		startMin.setValue(startMin.getPromptText());
	}
	
}