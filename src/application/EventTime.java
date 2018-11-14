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
	
	public static boolean isLeap(int year) {

		if (year % 4 == 0) {

			if (year % 100 == 0) {

				if (year % 400 == 0) {

					return true;

				} else {
					return false;
				}

			} else {

				// leap

				return true;

			}

		} else {

			// not leap

			return false;

		}
		
		
	}
	
	

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
