package application;
	

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// TODO: Program crashes when user clicks out before entering event

public class Main extends Application {
	
	@FXML private GridPane cal;
	private Rectangle [] [] recGrid = new Rectangle [7][5];
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		cal = new GridPane();
		cal.setPadding(new Insets(10,10,10,10));
		cal.setHgap(10);
		cal.setVgap(10);
		
		drawCalendar();
		
		Scene scene = new Scene(cal);
		primaryStage.setTitle("WITAssistant");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void drawCalendar() {
		int counter = 0;
		final int MAX_ROW = 10;
		final int MAX_SIZE = 34;
		int rowNum = -1;
		int colNum = 0;
		while((counter) < MAX_SIZE) {
			if(rowNum == MAX_ROW) {
				rowNum = 0;
				colNum++;
			}
			else {
				rowNum++;
			}
			counter++;
			DayBox dayBox = new DayBox(150,200,counter);
			dayBox.setOnMouseReleased(e -> {
				dayBox.addEvent();
		});
			cal.setConstraints(dayBox,rowNum,colNum);
			cal.getChildren().addAll(dayBox);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
