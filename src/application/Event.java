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
	
	public Event() {
		eventTitle = "";
		eventDesc = "";
		startTime = 0;
		endTime = 0;
	}
	
	public String getEventTitle() {
		return eventTitle;
	}



	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}



	public String getEventDesc() {
		return eventDesc;
	}



	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}



	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}



	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public String toString() {
		String output = ("Title: " + eventTitle + " Name: " + eventDesc + " Start Time: " + startTime + " End Time: " + endTime);
		return output;
	}
	
}
