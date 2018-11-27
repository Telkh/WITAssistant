package application;


public class Event {
	private String eventTitle;
	private String eventDesc;
	private String eventDate;
	private EventTime startTime;
	private EventTime endTime;
	private boolean isAllDay;
	
	public Event(String title, String name, String date, EventTime start, EventTime end) {
		eventDate = date;
		eventTitle = title;
		eventDesc = name;
		startTime = start;
		endTime = end;
		isAllDay = false;
	}
	
	public Event() {
		eventTitle = ""; //2
		eventDesc = "";
		eventDate = "00/00/0000"; //1
		startTime = new EventTime(0,0);
		endTime = new EventTime(0,0);
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

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public EventTime getStartTime() {
		return startTime;
	}

	public void setStartTime(EventTime startTime) {
		this.startTime = startTime;
	}

	public EventTime getEndTime() {
		return endTime;
	}

	public void setEndTime(EventTime endTime) {
		this.endTime = endTime;
	}

	public boolean isAllDay() {
		return isAllDay;
	}

	public void setAllDay(boolean isAllDay) {
		this.isAllDay = isAllDay;
	}

	public String toString() {
		String output = ("Title: " + eventTitle + " Name: " + eventDesc + " Start Time: " + startTime + " End Time: " + endTime);
		return output;
	}
	
}
