package application;



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
	@FXML private Button btnReset;
	@FXML private Button btnGoToPage;
	static String message;
	
	public void Submit() {
		message = tfInput.getText();
		String command = Command.commandTerm(message);
		tfInput.setText(command);
	}
	
	public void Browser() {
		message = tfInput.getText();
		String command = Command.commandTerm(message);
		if (!(command.contains("https"))) {
			tfInput.setText(command);
		}
		tfInput.setText("");
		URI myURI = null;
		try {
			myURI = new URI(command);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			Desktop.getDesktop().browse(myURI);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Reset() {
		tfInput.setText("");
	}
}
