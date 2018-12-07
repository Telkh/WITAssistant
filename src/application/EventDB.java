package application;

import java.util.ArrayList;
import java.util.TreeMap;
import csvHandler.CsvHandler;
/**
 * Handles the event dataBase
 * Add events to CSV file
 * Reads events from CSV file
 */
public class EventDB {

	private static TreeMap <String, Event> eventMap = new TreeMap <String, Event>(); //main event data holder
	static CsvHandler eventHandler = new CsvHandler("userData"); //creates CSV handler object. Enables reading and writitng of events.
	public EventDB() {
		loadFromCSV();
	}
	
	/*
	 * Adds Event object to TreeMap if there no value is attached to key
	 */
	public static void addToEventDB(String keyValue, Event event) {
		if(!eventMap.containsKey(keyValue)) {
			eventMap.put(keyValue, event);
			addToCSV();
		}
	}
	
	/*
	 * Returns true if there is a value attached to key
	 */
	public static boolean containsAtDate(String keyValue) {
		if(eventMap.containsKey(keyValue)) {
			return true;
		}
		return false;
	}
	
	// Load all information from CSV into TreeMap
	public static void loadFromCSV() {
		eventHandler.reader(eventMap);
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
