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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// TODO: Program crashes when user clicks out before entering event
// TODO: Create separate functions for drawing lower section, right-side panel, etc.
public class Main extends Application {
	
	@FXML private GridPane cal;
	@FXML private Button btnNext;
	@FXML private HBox lowerSection;
	@FXML private BorderPane bPane;
	private Rectangle [] [] recGrid = new Rectangle [7][5];
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent root  = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("WITAssistant");
		primaryStage.setScene(scene);
	
		primaryStage.setWidth(1800); // TODO: Set min width and height based on box dimensions
		primaryStage.setHeight(900);
		primaryStage.setResizable(false);
		primaryStage.widthProperty().addListener((obs, oldval, newval) -> {
		});
		primaryStage.show();
	}
		

	public static void main(String[] args) {
		launch(args);
//		GPA g = new GPA();
//		g.generateValue();
//		g.toString();
	}
}
