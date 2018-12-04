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
		//CsvHandler.printMap(eventMap); //debug
	}
	/**
	 * Creates a CSV file with the data in the treeMap
	 */
	private static void addToCSV() {
		eventHandler.writer(eventMap);
	}
/**
 * Gets all the events in a day
 * @param dateToSearch
 * @return ArrayList with events.
 */
	public static ArrayList<Event> getEvents(String dateToSearch) {
		ArrayList<Event> dayEvents = new ArrayList<>(); //Creates arrayList
		for(String key: eventMap.keySet()) { //loops trough all the keys.
			Event  event = eventMap.get(key); //create Event object to pass in the ArrayList
			int dashIndex = key.indexOf("-"); // Finds index of character '-' in key
			String currentDate = key.substring(0,dashIndex); // Stores date of entry in TreeMap
			if(currentDate.equals(dateToSearch)) { // if the key contains the date. Add to ArrayList
				dayEvents.add(event);
			}
		}
		return dayEvents;
	}

}
