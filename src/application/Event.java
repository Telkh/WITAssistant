package application;


public class Event {
	private String eventTitle;
	private String eventDesc;
	private String eventDate;
	private EventTime startTime;
	private EventTime endTime;
	
	public Event(String title, String description, String date, EventTime start, EventTime end) {
		eventDate = date;
		eventTitle = title;
		eventDesc = description;
		startTime = start;
		endTime = end;
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

	public String toString() {
		String output = String.format("Title: %s%nDescription: %s%nStart time: %s  End Time: %s", eventTitle, eventDesc, startTime.getTimeValue(), endTime.getTimeValue());
		return output;
	}
	/**
	 * Generate a key for an event.
	 * @param event
	 * @return key
	 */
	public String generateKey() {
		System.out.println("In Event Class: " + this.eventDate);
		String key = String.format("%s-%s", this.eventDate, this.startTime.getTimeValue());
		return key;
	}
}
