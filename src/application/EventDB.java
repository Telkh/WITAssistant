package application;

import java.util.ArrayList;
import java.util.TreeMap;

import csvHandler.CsvHandler;

public class EventDB {

	private static TreeMap <String, Event> eventMap = new TreeMap <String, Event>();
	static CsvHandler eventHandler = new CsvHandler("userData");
	public EventDB() {
		loadFromCSV();
	}
	
	public static void addToEventDB(String keyValue, Event event) {
		if(eventMap.containsKey(keyValue)) {
			System.out.println("EventDB already contains data on date " + keyValue);
			System.out.println(eventMap.get(keyValue).toString());
		}
		else {
			eventMap.put(keyValue, event);
			System.out.println("Received event: " + event.toString());
			CsvHandler.printMap(eventMap);
			addToCSV();
		}
		
	}
	
	public static boolean containsAtDate(String keyValue) {
		if(eventMap.containsKey(keyValue)) {
			return true;
		}
		return false;
	}
	
	// Load all information from CSV into HashMap
	public static void loadFromCSV() {
		
		eventHandler.reader(eventMap);
		CsvHandler.printMap(eventMap);
	}
	
	private static void addToCSV() {
		eventHandler.writter(eventMap);
	}
//	public static ArrayList<String> getEvents() {
//		ArrayList<String> event = new ArrayList<>();
//		for(String key: )
//		eventMap.containsKey(key)
//		return event;
//	}
//	public static ArrayList<Event> getListAt(String keyValue){
//		return eventMap.get(keyValue);
//	}
}
