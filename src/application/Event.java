package application;

public class Event {
	private String eventTitle;
	private String eventDesc;
	private int startTime;
	private int endTime;
	
	public Event(String title, String name, int start, int end) {
		eventTitle = title;
		eventDesc = name;
		startTime = start;
		endTime = end;
	}
	
	public String getTitle() {
		return eventTitle;
	}
	
	public String getDescription() {
		return eventDesc;
	}
	
	
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	
	public String toString() {
		String output = ("Title: " + eventTitle + " Name: " + eventDesc + " Start Time: " + startTime + " End Time: " + endTime);
		return output;
	}
	
}
