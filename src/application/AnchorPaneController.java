package application;

import java.io.IOException;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// TODO: Ask about controller class: Should I have methods for each component (Buttons, etc.) in one controller class?

public class AnchorPaneController {
	@FXML GridPane cal;
	@FXML HBox topSection;
	@FXML SplitPane splitPane;
	@FXML AnchorPane leftSection;
	@FXML Label currentDateLabel;
	private String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private int [] numDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int monthValue;
	private int yearValue;
	private ContextMenu contextMenu;
	private MenuItem itemEdit;
	private Event currentEvent;
	
	public void drawCalendar(int numDays) {
		int counter = 0;
		final int MAX_ROW = 10; //TODO: Make max-row 7
		final int MAX_SIZE = numDays;
		int rowNum = -1;
		int colNum = 0;
		int length = 100;
		int width = 200;
		int paddingValue = 10;
		while((counter) < MAX_SIZE) {
			if(rowNum == MAX_ROW) {
				rowNum = 0;
				colNum++;
			}	
			else {
				rowNum++;
			}
			counter++;
			DayBox dayBox = new DayBox(length,width,counter);
			dayBox.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>(){
				@Override
				public void handle(ContextMenuEvent event) {
					contextMenu.show(dayBox,event.getScreenX(), event.getScreenY());
				}
			});
			GridPane.setConstraints(dayBox,rowNum,colNum);
			cal.getChildren().addAll(dayBox);
		}
		
	}
	
	public void nextMonth() {
		if(monthValue == 12) {
			monthValue = 1;
			yearValue++;
		} 
		else {
			monthValue++;
		}
		currentDateLabel.setText(months[monthValue - 1] + " " + yearValue);
		cal.getChildren().clear();
		if(EventTime.isLeap(yearValue)) {
             numDays[1] = 29;
		 } else {
			 numDays[1] = 28;
		 }
		setLeapYear();
		drawCalendar(numDays[monthValue - 1]);
	}
	
		
	public void previousMonth() {
		if(monthValue == 1) {
			monthValue = 12;
			yearValue--;
		}
		else {
			monthValue--;
		}
		currentDateLabel.setText(months[monthValue - 1] + " " + yearValue);
		cal.getChildren().clear();
		setLeapYear();
		drawCalendar(numDays[monthValue - 1]);
	}
	
	public void setLeapYear() {
		if(EventTime.isLeap(yearValue)) {
            numDays[1] = 29;
		 } else {
			 numDays[1] = 28;
		 }
	}
	
	public void setContextMenu() {
		contextMenu = new ContextMenu();
		itemEdit = new MenuItem("Edit");
		EventFormController EFC = new EventFormController();
		itemEdit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Event temp = EFC.getEvent();
					EFC.initialize();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contextMenu.getItems().add(itemEdit);
	}
	
	public Stage showEventForm(Event event) {
		return null;	
	}
	
	public void initialize() {
		monthValue = LocalDateTime.now().getMonthValue();
		yearValue = LocalDateTime.now().getYear();
		currentDateLabel.setText(months[monthValue - 1] + " " + yearValue);
		leftSection.setMaxWidth(1100);
		leftSection.minWidthProperty().bind(splitPane.widthProperty().multiply(.685));
		drawCalendar(numDays[monthValue - 1]);
		setContextMenu();
	}
	public static boolean isLeap(int year) {
		if (year%4 == 0) {
			if (year%100 == 0) {
				if(year%400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				//leap
				return true;
			}
		} else {
			//not leap
			return false;
		}
	}
	
	public static int getDays(String monthName, int year) {
		String [] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		int [] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(isLeap(year) == true) {
			days[1] = 29;
		}
	
		//k
		return 0;
	}
}


