package application;

import java.util.HashMap;

import javafx.event.EventHandler;

public class Command {
	private String message;
	
	public void onMessageRecieved(EventHandler event) {
        message = event.toString();
        if (message.toString().startsWith("~")) {
            String[] arr = message.toString().split(" ");
            if(hmap.containsKey(arr[0].toLowerCase())) {
                hmap.get(arr[0].toLowerCase()).run();
            }
        }
    }
	
	private HashMap<String, Runnable> hmap;
	
	public Command() {
		this.hmap = new HashMap<String, Runnable>() {
			{
			    put("~faculty", () -> faculty(message));
			    put("~search", () -> search(message));
			    put("~help", () -> help(message));
			    put("~about", () -> about(message));
			}
		};
	}
	
	public String faculty(String message) {
		if (message.equals("~faculty")) {
			return "Please enter a term after the command";
		}
		
		if (message.contains(" ")) {
			message = message.toLowerCase().replace(" ", "+");
			return "https://wit.edu/wentworth-directory?name="+ message + "&field_colleges_target_id=All&field_department_target_id=All";
		}
		
		return "https://wit.edu/wentworth-directory?name="+ message.toLowerCase() + "&field_colleges_target_id=All&field_department_target_id=All";
	}
	public String search(String message) {
		if (message.equals("~search")) {
			return "Please enter a term after the command";
		}
		
		if (message.contains(" ")) {
			message = message.toLowerCase().replace(" ", "%20");
			return "https://wit.edu/search/" + message;
		}
		
		return "https://wit.edu/search/" + message;
	}
	public String help(String message) {
		return "";
	}
	public String about(String message) {
		return "";
	}
}
