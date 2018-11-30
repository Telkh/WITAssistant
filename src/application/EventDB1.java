package application;


import java.util.ArrayList;
import java.util.TreeMap;

public class EventDB1 {
	private static TreeMap <String, Event> eventMap = new TreeMap<String, Event>();
	
//	public static void addToEventDB(String keyValue, Event event) {
//		if(eventMap.containsKey(keyValue)) {
//			System.out.println("EventDB already contains data on date " + keyValue);
//			eventMap.get(keyValue).add(event);
//		}
//		else {
//			ArrayList<Event> eventList = new ArrayList<Event>();
//			eventList.add(event);
//			
//			eventMap.put(keyValue, eventList);
//			System.out.println("Received event: " + event);
//		}
		
//	}
	
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
	
//	public static ArrayList<Event> getListAt(String keyValue){
//		return eventMap.get(keyValue);
//	}
}