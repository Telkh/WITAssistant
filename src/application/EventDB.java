package application;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDB {

	private static HashMap <String, ArrayList<Event>> eventMap = new HashMap <String, ArrayList<Event>>();
	
	public static void addToEventDB(Event event, String keyValue) {
		if(eventMap.containsKey(keyValue)) {
			System.out.println("EventDB already contains data on date " + keyValue);
			eventMap.get(keyValue).add(event);
		}
		else {
			ArrayList<Event> eventList = new ArrayList<Event>();
			eventList.add(event);
			
			eventMap.put(keyValue, eventList);
			System.out.println("Received event: " + event);
		}
		
	}
	
	public static boolean containsAtDate(String keyValue) {
		if(eventMap.containsKey(keyValue)) {
			return true;
		}
		return false;
	}
	
	// Load all information from CSV into HashMap
	private void loadFromCSV() {
		
	}
	
	private void addToCSV() {
		
	}
	
	public static ArrayList<Event> getListAt(String keyValue){
		return eventMap.get(keyValue);
	}
}
