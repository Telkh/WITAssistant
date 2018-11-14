package application;

public class EventTime implements Comparable {
	private int hour;
	private int minute;
	private String meridiem; // AM or PM
	
	public EventTime(int H, int M, String MD) {
		hour = H;
		minute = M;
		meridiem = MD;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
