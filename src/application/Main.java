package application;
	

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
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
		cal.setOnMouseClicked(e -> {
			System.out.println("X: " + e.getX() + " Y: " + e.getY());
			
		});
		
	}
	
	public void drawCalendar() {
		int counter = 0;
		final int MAX_ROW = 5;
		final int MAX_SIZE = 34;
		int rowNum = -1;
		int colNum = 0;
		/*for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 5; j++) {
				counter++;
				Label dateNum = new Label(Integer.toString(counter));
				
				
				Rectangle dayBox = new Rectangle(0, 0, 100, 100);
				day	Box.setFill(Color.WHITE);
				dayBox.setStroke(Color.BLACK);
				
				StackPane layout = new StackPane();
				layout.getChildren().addAll(dayBox,dateNum);
				
				cal.setConstraints(layout,j,i);
				cal.getChildren().addAll(layout);
			}
		}*/
		while((counter) < MAX_SIZE) {
			if(rowNum == MAX_ROW) {
				rowNum = 0;
				colNum++;
			}
			else {
				rowNum++;
			}
			counter++;
			
			Label dateNum = new Label(Integer.toString(counter));
			dateNum.setAlignment(Pos.CENTER);
			Rectangle dayBox = new Rectangle(0, 0, 100, 100);
			dayBox.setFill(Color.WHITE);
			dayBox.setStroke(Color.BLACK);
			
			VBox layout = new VBox(2);
			layout.setPrefWidth(dayBox.getWidth());
			
			layout.getChildren().addAll(dateNum, dayBox);
			
			cal.setConstraints(layout,rowNum,colNum);
			cal.getChildren().addAll(layout);
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
