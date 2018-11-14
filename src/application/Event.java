package application;

public class Event {
	private String eventTitle;
	private String eventName;
	private int startTime;
	private int endTime;
	
	public Event(String title, String name, int start, int end) {
		eventTitle = title;
		eventName = name;
		startTime = start;
		endTime = end;
	}
	
	
	
	public String toString() {
		String output = ("Title: " + eventTitle + " Name: " + eventName + " Start Time: " + startTime + " End Time: " + endTime);
		return output;
	}
	
}
