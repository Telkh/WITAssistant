package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
	private static Event event;
	private boolean [] isFilled = new boolean[6];
	
	public void initialize() {
		lblPrompt.setText("Add Event for " + EventTime.getDate());
		event = new Event();
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
	
	/*
	 * Updates Event object's properties whenever the user enters
	 * or changes information.
	 */
	public void setEvent() {
		
		tfTitle.textProperty().addListener((obs, oldval, newval) -> {
			isFilled[0] = true;
				event.setEventTitle(newval);
		});
		
		tfDesc.textProperty().addListener((obs, oldval, newval) -> {
			isFilled[1] = true;
			event.setEventDesc(newval);
		});
		
		event.setEventDate(EventTime.getDateKeyFormat()); //sets date
		
		startHour.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			if(newval != null) {
				isFilled[2] = true;
				event.getStartTime().setHour(newval);
			}
		});
		
		startMin.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			if(newval != null) {
				isFilled[3] = true;
				event.getStartTime().setMinutes(newval);
			}
		});
		
		endHour.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			if(newval != null) {
				isFilled[4] = true;
				event.getEndTime().setHour(newval);
			}
		});
		
		endMin.getSelectionModel().selectedItemProperty().addListener((options, oldval, newval) -> {
			if(newval != null) {
				isFilled[5] = true;
				event.getEndTime().setMinutes(newval);
			}
		});
	}
	/**
	 * adds event gathered to DB
	 */
	public void addToDB() {
		if(allFieldsFilled()) {
			EventDB.addToEventDB(event.generateKey(), event);
			clearInput();
		}
		else {
			lblWarning.setVisible(true);
		}
	}
	/*
	 * Returns true if all of the form fields have been filled
	 */
	private boolean allFieldsFilled() {
		for(int i = 0; i < isFilled.length; i++) {
			if(!isFilled[i]) {
				return false;
			}
		}
		return true;	
	}
	
	/*
	 * Disables the form when the user submits information or cancels
	 * Resets isFilled array
	 */
	public void clearInput() {
		if(lblWarning.isVisible()) {
			lblWarning.setVisible(false);
		}
		for(int i = 0; i < isFilled.length; i++) {
			isFilled[i] = false;
		}
		anchorPane.setDisable(true);
	}
	/*
	 * Action for cancel button
	 */
	public void cancel() {
		clearInput();
	}
	
}