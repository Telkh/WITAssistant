package application;

import java.util.HashMap;

//TODO Get to work on the commands and WITAssistant as a whole
public class Command {
	
	private HashMap<String, Runnable> hmap;
	
	public Command() {
		this.hmap = new HashMap<String, Runnable>() {
			{
			    System.out.println("test2");
			}//
		};
	}
}
