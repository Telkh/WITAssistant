package application;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class MainController {
	
	@FXML private SplitPane splitPane;
	@FXML private AnchorPane leftSection;
	@FXML private Label currentDateLabel;
	@FXML private HBox lowerSection;
	@FXML private Button btnPrev;
	@FXML private Button btnNext;
	@FXML private Button btnRefresh;
	@FXML private VBox vContainer;
	@FXML private TabPane rightSection;
	@FXML private Tab tabAddEvent;
	@FXML private Tab tabCalc;
	@FXML private AnchorPane calcPanel;
	@FXML private AnchorPane addEventPanel;
	@FXML private Tab tabAssistant;
	@FXML private AnchorPane assistantPanel;
	private FXMLLoader fxmlLoader = new FXMLLoader();
	private SingleSelectionModel<Tab> selectionModel;
	
	/* 
	 * Draws calendar grid using a nested for-loop. The inner loop populates an HBox container
	 * with DayBox objects and binds their with and height with the HBox. The outer loop adds the previous
	 * HBox to a VBox object and creates a new HBox object for the next row.
	 */
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
				dayBox.getEvents();
				rowContainer.getChildren().add(dayBox);
				setDayProperties(dayBox);
			}
			vContainer.getChildren().add(rowContainer);
			rowContainer = new HBox();
		}
	}
	/*
	 * Defines the setOnMouseClicked action for each DayBox. When clicked, updates the current day
	 * in EventTime class. If the plus-icon is clicked, the AddEvent tab is shown.
	 * 
	 */
	public void setDayProperties(DayBox dayBox) {
		dayBox.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				EventTime.setDay(dayBox.getDay());
				if(dayBox.isInPlusBounds(event.getX(), event.getY())) {
					try {
						addEventPanel = fxmlLoader.load(getClass().getResource("EventForm.fxml"));
						tabAddEvent.setContent(addEventPanel);
						
					} catch (IOException e) {
						e.printStackTrace();
					}		
					selectionModel.select(2);
				}
			}
		});
	}
	/*
	 * Goes to next month, redraws calendar, and updates label
	 */
	public void nextButtonEvent() {
		EventTime.nextMonth();
		drawCalendar();
		setDateLabel();
	}
	
	/*
	 * Goes to previous month, redraws calendar, and updates label
	 */
	public void previousButtonEvent() {
		EventTime.previousMonth();
		drawCalendar();
		setDateLabel();
	}
	
	/*
	 * Sets label to current date in EventTime
	 */
	public void setDateLabel() {
		currentDateLabel.setText(EventTime.getMonthName() + " " + EventTime.getYear());
	}
	
	/*
	 * Initializes calendar and TabPane
	 */
	public void initialize() {
		selectionModel = rightSection.getSelectionModel();	
		splitPane.setDividerPosition(0, .75);
		EventTime.initializeDate();
		try {
			addEventPanel = fxmlLoader.load(getClass().getResource("EventForm.fxml"));
			assistantPanel = fxmlLoader.load(getClass().getResource("Assistant.fxml"));
			calcPanel = fxmlLoader.load(getClass().getResource("Calculator.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addEventPanel.setDisable(true);
		tabAssistant.setContent(assistantPanel);
		tabCalc.setContent(calcPanel);
		tabAddEvent.setContent(addEventPanel);
		currentDateLabel.setText(EventTime.getMonthName() + " " + EventTime.getYear());
		drawCalendar();
		
	}
}

