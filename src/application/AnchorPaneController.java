package application;

import java.awt.Font;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class AnchorPaneController {
	
	@FXML SplitPane splitPane;
	@FXML AnchorPane leftSection;
	@FXML AnchorPane rightSection;
	@FXML AnchorPane contextPanel;
	@FXML Label currentDateLabel;
	@FXML VBox vContainer;
	
	private MenuItem itemEdit;
	private double currentWidth;
	private double currentHeight;
	private FXMLLoader fxmlLoader = new FXMLLoader();
	
	public void drawCalendar() {
		vContainer.getChildren().clear();
		int counter = 0;
		HBox rowContainer = new HBox();
		for(int col = 0; col < 5; col++) {
			for(int row = 0; row < 7; row++) {
				if(counter == EventTime.getNumDays()) {
					break;
				}
				counter++;
				DayBox dayBox = new DayBox(counter); 
				dayBox.widthProperty().bind(leftSection.widthProperty().divide(7.1));
				dayBox.heightProperty().bind(leftSection.heightProperty().divide(6));
				setDayProperties(dayBox);
				if(EventDB.containsAtDate(EventTime.getDate())) {
					dayBox.getEvents();
				}
				rowContainer.getChildren().add(dayBox);
			}
			vContainer.getChildren().add(rowContainer);
			rowContainer = new HBox();
		}
	}
	
	public void setDayProperties(DayBox dayBox) {
		dayBox.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				rightSection.getChildren().clear();
				EventTime.setDay(dayBox.getDay()); // Set current day to day selected by user
				System.out.println(EventTime.getDate());
				if(dayBox.isInImageBounds(event.getX(), event.getY())) {
					try {
						contextPanel = fxmlLoader.load(getClass().getResource("EventForm.fxml"));
						contextPanel.setPrefWidth(rightSection.getWidth());
						EventFormController EFC = (EventFormController)fxmlLoader.getController();
						rightSection.getChildren().addAll(contextPanel);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					try {
							System.out.println("Assistant");
							contextPanel = fxmlLoader.load(getClass().getResource("Overview.fxml"));
							contextPanel.setPrefWidth(rightSection.getWidth());
							rightSection.getChildren().add(contextPanel);
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		});
		
		dayBox.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dayBox.deselectDayBox();
			}
		});
		dayBox.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dayBox.selectDayBox();
			}
		});
	}
	
	public void drawDayOverview() {
		/*tfOverview.getChildren().clear(); // Reset TextFlow before displaying new text
		if(EventDB.containsAtDate(EventTime.getDate())) {
			ArrayList <Event> eventList = EventDB.getListAt(EventTime.getDate());
			for(int i = 0; i < eventList.size(); i++) {
				drawEventInformation(eventList.get(i));
			}
		}
		else {
			Text emptyMessage = new Text("There are no events on " + EventTime.getDate());
			tfOverview.getChildren().add(emptyMessage);
		}*/
	}
	
	public void drawEventInformation(Event event) {
	/*	Text title = new Text("\n" + event.getEventTitle());
		title.setStyle("-fx-font-weight: bold");
		Text desc = new Text("\n" + event.getEventDesc());
		tfOverview.getChildren().addAll(title,desc); */
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

	
	public void initialize() {
		//contextPanel.layoutXProperty().bind(rightSection.layoutXProperty());
		contextPanel.setMinSize(0, 0);
		rightSection.widthProperty().addListener((obs, oldval, newval) -> {
			//System.out.println("Testing" + newval.doubleValue());
			contextPanel.setPrefWidth(newval.doubleValue());
		});
		splitPane.setDividerPosition(0, .75);
		
		EventTime.initializeDate();

		currentDateLabel.setText(EventTime.getMonthName() + " " + EventTime.getYear());
		
		drawCalendar();
	}
}


