package application;

import java.awt.Font;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
	@FXML private AnchorPane addEventPanel;
	@FXML private Tab tabAssistant;
	@FXML private AnchorPane assistantPanel;
	private FXMLLoader fxmlLoader = new FXMLLoader();
	private SingleSelectionModel<Tab> selectionModel;
	
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
				EventTime.setDay(dayBox.getDay()); // Set current day to day selected by user	
				System.out.println(EventTime.getDate() + " " + dayBox.getDay());
				if(dayBox.isInPlusBounds(event.getX(), event.getY())) {
					try {
						addEventPanel = fxmlLoader.load(getClass().getResource("EventForm.fxml"));
						tabAddEvent.setContent(addEventPanel);
						addEventPanel.setDisable(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
					selectionModel.select(2);
					leftSection.setDisable(true);
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
		selectionModel = rightSection.getSelectionModel();	
		splitPane.setDividerPosition(0, .75);
		EventTime.initializeDate();
		try {
			addEventPanel = fxmlLoader.load(getClass().getResource("EventForm.fxml"));
			assistantPanel = fxmlLoader.load(getClass().getResource("Assistant.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addEventPanel.disabledProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldval, Boolean newval) {
				System.out.println("Changed");
			}
		});

		tabAssistant.setContent(assistantPanel);
		tabAddEvent.setContent(addEventPanel);
		currentDateLabel.setText(EventTime.getMonthName() + " " + EventTime.getYear());
		drawCalendar();
		
	}
}

