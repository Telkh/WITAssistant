package application;

import java.awt.Font;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class AnchorPaneController {
	@FXML GridPane cal;
	@FXML SplitPane splitPane;
	@FXML AnchorPane leftSection;
	@FXML AnchorPane rightSection;
	@FXML TextFlow tfOverview;
	@FXML Label currentDateLabel;
	
	private ContextMenu contextMenu;
	private MenuItem itemEdit;
	private Event currentEvent;

	public void drawCalendar() {
		cal.getChildren().clear();
		int counter = 0;
		final int MAX_ROW = 6;
		int rowNum = -1;
		int colNum = 0;
		int length = 200;
		int width = 200;
		int paddingValue = 10;
		while((counter) < EventTime.getNumDays()) {
			if(rowNum == MAX_ROW) {
				rowNum = 0;
				colNum++;
			}	
			else {
				rowNum++;
			}
			counter++;
			DayBox dayBox = new DayBox(length,width,counter);
			dayBox.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					EventTime.setDay(dayBox.getDay()); // Set current day to day selected by user
					if(event.getButton().toString().equals("SECONDARY")) {
						contextMenu.show(dayBox,event.getScreenX(), event.getScreenY());
					}
					else {
						drawDayOverview();
					}
				}
			});
			
			// Draw labels on DayBox if events exist on that date
			if(EventDB.containsAtDate(EventTime.getDate())) {
				dayBox.drawEvents(EventDB.getListAt(EventTime.getDate()));
			}
			GridPane.setConstraints(dayBox,rowNum,colNum);
			cal.getChildren().addAll(dayBox);
		}
	}
	
	public void drawDayOverview() {
		tfOverview.getChildren().clear(); // Reset TextFlow before displaying new text
		if(EventDB.containsAtDate(EventTime.getDate())) {
			ArrayList <Event> eventList = EventDB.getListAt(EventTime.getDate());
			for(int i = 0; i < eventList.size(); i++) {
				drawEventInformation(eventList.get(i));
			}
		}
		else {
			Text emptyMessage = new Text("There are no events on " + EventTime.getDate());
			tfOverview.getChildren().add(emptyMessage);
		}
	}
	
	public void drawEventInformation(Event event) {
		Text title = new Text("\n" + event.getEventTitle());
		title.setStyle("-fx-font-weight: bold");
		Text desc = new Text("\n" + event.getEventDesc());
		tfOverview.getChildren().addAll(title,desc);
	}
	
	
	
	public void nextButtonEvent() {
		EventTime.nextMonth();
		drawCalendar();
		setDateLabel();
	}
	
	public void previousButtonEvent() {
		EventTime.previousMonth();
		drawCalendar();
		setDateLabel();
	}
	
	public void setDateLabel() {
		currentDateLabel.setText(EventTime.getMonthName() + " " + EventTime.getYear());
	}

	
	public void setContextMenu() {
		contextMenu = new ContextMenu();
		itemEdit = new MenuItem("Edit");
		EventForm eventForm = new EventForm();
		itemEdit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // TODO: Refresh calendar here
				eventForm.drawForm();
				EventDB.addToEventDB(eventForm.getEvent(), EventTime.getDate());
				drawCalendar();
			}
		});
		contextMenu.getItems().add(itemEdit);
	}
	
	public void initialize() {
		EventTime.initializeDate();
		int height = 1025;
		int width = 1425;
		cal.setMinSize(width, height);
		cal.setMaxSize(width, height);
		leftSection.setMinSize(cal.getMaxWidth(), cal.getMaxHeight());
		leftSection.setMaxSize(cal.getMaxWidth(), cal.getMaxHeight());
		currentDateLabel.setText(EventTime.getMonthName() + " " + EventTime.getYear());
		drawCalendar();
		setContextMenu();
	}
	
	
	
}


