package application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

public class AssistantController {

	@FXML private Label lblPrompt;
	@FXML private TextField tfInput;
	@FXML private HBox hContainer;
	@FXML private Button btnSubmit;
	@FXML private TextFlow textFlowOutput;

	static String message;
	
	public void ClickMe() {
		message = tfInput.getText();
		String command = Command.commandTerm(message);
		tfInput.setText(command);
	}
}
