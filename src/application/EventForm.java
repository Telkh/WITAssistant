package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class EventForm {
	private GridPane layout;
	private Stage stage;
	private TextField tfTitle;
	private TextField tfDesc;
	private TextField tfStartTime;
	private TextField tfEndTime;
	private Label lblTitle;
	private Label lblDesc;
	private Label lblStartTime;
	private Label lblEndTime;
	private Button btnSubmit;
	private Event newEvent;
	private CheckBox allDayCheck;
	public void drawForm() {
		stage = new Stage();
		layout = new GridPane();
		
		tfTitle = new TextField();
		tfDesc = new TextField();
		tfStartTime = new TextField();
		tfEndTime = new TextField();
		
		lblTitle = new Label("Title");
		lblDesc = new Label("Description");
		lblStartTime = new Label("Start Time");
		lblEndTime = new Label("End Time");
		
		allDayCheck = new CheckBox("All Day Event");
		
		btnSubmit = new Button("Submit");
		newEvent = new Event();
		setGrid();
		Scene scene = new Scene(layout);
		
		stage.setScene(scene);
		stage.setMinHeight(300);
		stage.setMinWidth(400);
		btnSubmit.setOnMouseClicked(e -> { // TODO: Check if input is valid (Such as times)
			setEvent();
			stage.close();
		});
		allDayCheck.setOnAction(e -> {
			
			
			if(allDayCheck.isSelected()) {
				tfStartTime.setDisable(true);
				tfEndTime.setDisable(true);
				tfStartTime.setText("");
				tfEndTime.setText("");
			}
			else {
				
				tfStartTime.setDisable(false);
				tfEndTime.setDisable(false);
			}
			newEvent.setStartTime(tfStartTime.getText());
			newEvent.setEndTime(tfEndTime.getText());
			System.out.println(newEvent.getStartTime() + " Testing " + newEvent.getEndTime());
		});
		stage.showAndWait();
	}
	
	public void setEvent() {
		newEvent.setEventTitle(tfTitle.getText());
		newEvent.setEventDesc(tfDesc.getText());
		newEvent.setStartTime(tfStartTime.getText());
		newEvent.setEndTime(tfEndTime.getText());
	}
	
	public void setGrid() {
		layout.addRow(0, lblTitle, tfTitle);
		layout.addRow(1, lblDesc, tfDesc);
		layout.addRow(2, lblStartTime, tfStartTime);
		layout.addRow(3, lblEndTime, tfEndTime);
		layout.addRow(4, allDayCheck, btnSubmit);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setHgap(10);
		layout.setVgap(10);
	}
	
	public Event getEvent() {
		return newEvent;
	}
	
}