package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
		primaryStage.setWidth(1500);
		primaryStage.setHeight(1000);
		primaryStage.setMinWidth(750);
		primaryStage.setMinHeight(500);
		primaryStage.heightProperty().addListener((obs, oldval, newval) -> {
		});
		primaryStage.show();	
	}
	

	public static void main(String[] args) {
		EventDB.loadFromCSV();
		launch(args);
	}
}
