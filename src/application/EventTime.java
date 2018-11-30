package application;

import java.time.LocalDateTime;
import java.util.HashMap;

// TODO: Ask if it makes sense in terms of organization to have static methods along with object methods in same class


public class EventTime implements Comparable, CurrentTime {
	private int hour;
	private int minute;

	private static int currentMonth;
	private static int currentYear;
	private static int currentDay;
	private static String currentDate;

	public EventTime(int H, int M) {
		hour = H;
		minute = M;
	}
	
	public EventTime(String time) {
		hour = Integer.parseInt(timeFormat(time)[0]);
		minute = Integer.parseInt(timeFormat(time)[1]);
	}
	
	/**
	 * Separates the time from a string "12:32" into 12 and 32;
	 * @param time 
	 * @return Array of 2 values. [H][M]
	 */
	public String[] timeFormat(String time) {
		String [] timeArray = new String[2];
		int separator = time.indexOf(":");
		timeArray[0] = time.substring(0, separator);
		timeArray[1] = time.substring(separator + 1);
		return timeArray;
	}
	
	public EventTime() {
		hour = 0;
		minute = 0;
	}
	
	public void setHour(int h) {
		hour = h;
	}
	
	public void setHour(String h) {
		hour = Integer.parseInt(h);
	}
	
	public void setMinutes(int min) {
		minute = min;
	}
	
	public void setMinutes(String min) {
		minute = Integer.parseInt(min);
	}
	
	
	public int getHour() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public String getTimeValue() {
		return(hour + ":" + minute);
	}
	
	public static boolean isValidTime(String timeValue) {
		if(timeValue.length() == 4) {
			
		}
		return false;
	}
	
	public static void nextMonth() {
		if(currentMonth == 12) {
			currentMonth = 1;
			currentYear++;
		}
		else {
			currentMonth++;
		}
		setLeapYear();
	}
	
	public static void previousMonth() {
		if(currentMonth == 1) {
			currentMonth = 12;
			currentYear--;
		}
		else {
			currentMonth--;
		}
		setLeapYear();
	}

	private static void setLeapYear() {
		if(isLeap(currentYear)) {
			numDays[1] = 29;
		}
		else {
			numDays[1] = 28;
		}
		
	}
	
	
	public static void initializeDate() {
		currentMonth = LocalDateTime.now().getMonthValue();
		currentYear = LocalDateTime.now().getYear();
		currentDay = 1;
	}

	private static boolean isLeap(int year) {
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
	
	public static String getMonthName() {
		return(months[currentMonth - 1]);
	}
	
	public static int getMonthNumber() {
		return currentMonth;
	}
	
	public static int getNumDays() {
		return(numDays[currentMonth - 1]);
	}
	
	public static int getYear() {
		return currentYear;
	}
	
	public static void setDay(int dayNum) {
		currentDay = dayNum;
	}
	
	public static String getDate() {
		currentDate = (currentMonth + "/" + currentDay + "/" + currentYear);
		return currentDate;
	}
	public static String getDateKeyFormat() {
		currentDate = String.format("%d/%d/%d", currentYear, currentMonth, currentDay); 
		return currentDate;
	}
	
	@Override
	public int compareTo(Object arg0) {
		return 0;
	}
	
	
	
}
