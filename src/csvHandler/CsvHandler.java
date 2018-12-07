package csvHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import application.Event;
import application.EventTime;
import gpaCalculator.GPA;

/**
 * Handles CSV file Reads and writes CSV files in the format key, title,
 * description, start time, end time Reads and writes GPA data in the format
 * Course, grade, credits
 */
public class CsvHandler {
	String fileName; // file name
	int numColumns = 5;
	String readError = String.format("The path '%s' is invalid. Refer to 'csvHandler/CSVhandler'.", getPath(fileName));

	public CsvHandler(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * add folder and extension to filename
	 * 
	 * @param fileName
	 * @return
	 */
	private String getPath(String fileName) {
		return String.format("calendarData/%s.csv", fileName);
	}

	/**
	 * Reads all the line in a CSV file and puts them in TreeMap
	 * 
	 * @param TreeMap<String, Events> events
	 * @return events
	 */
	public TreeMap<String, Event> reader(TreeMap<String, Event> events) {
		File fileIn = new File(getPath(fileName)); // file object
		try (Scanner in = new Scanner(fileIn)) { // try to read the file
			while (in.hasNextLine()) {
				String line = addComma(in.nextLine());// add a comma to the end of the line
				// add elements to the map
				// key, title, description, start time, end time
				events.put(getData(line, 0), new Event(getData(line, 1), getData(line, 2), getData(line, 0),
						new EventTime(getData(line, 3)), new EventTime(getData(line, 4))));
			}
		} catch (FileNotFoundException ex1) {
			System.out.println(readError);
			System.exit(1);
		}
		return events;
	}

	/**
	 * Reads GPA data and stores it in an ArrayList<GPA>
	 * 
	 * @param myGPA
	 * @return ArrayList<GPA>
	 */
	public ArrayList<GPA> gpaReader(ArrayList<GPA> myGPA) {
		File fileIn = new File(getPath(fileName));
		try (Scanner in = new Scanner(fileIn)) {
			String line = addComma(in.nextLine());// add a comma to the end of the line
			// add elements to arrayList in the format Course, grade, credits
			myGPA.add(new GPA(getData(line, 0), getData(line, 1), Integer.parseInt(getData(line, 2))));

		} catch (FileNotFoundException e) {
			System.out.println(readError);
			System.exit(1);
		}
		return myGPA;
	}
	
	/**
	 * Writes Treemap to CSV file.
	 * @param eventMap
	 */
	public void writer(TreeMap<String, Event> eventMap) {
		File fileOut = new File(getPath(fileName)); //file object
		try (PrintWriter fout = new PrintWriter(fileOut)) {
			for (String date : eventMap.keySet()) {//loops trough every event on the map
				Event event = eventMap.get(date); //initializes  a temporary event to access the data stored.
				//Writes the data in to the file. All previous information in the file is deleted.
				String line = String.format("%s,%s,%s,%s,%s", date, event.getEventTitle(), event.getEventDesc(),
						event.getStartTime().getTimeValue(), event.getEndTime().getTimeValue());
				fout.println(line);
			}

		} catch (FileNotFoundException ex2) {
			System.out.println("File unable to write Event");
			System.exit(2);
		}
	}

	/**
	 * Writes GPA elements into a CSV file
	 * @param myGPA
	 */
	public void GPAwriter(ArrayList<GPA> myGPA) {
		File fileOut = new File(getPath(fileName)); //file object
		try (PrintWriter fout = new PrintWriter(fileOut)) {
			for (int i = 0; i < myGPA.size(); i++) { //loops trough array
				//Prepares string to write
				String line = String.format("%s,%s,%d", myGPA.get(i).getCourse(), myGPA.get(i).getGrade(),
						myGPA.get(i).getCredits());
				fout.println(line);
			}
		} catch (FileNotFoundException ex2) {
			System.out.println("File unable to write GPA");
			System.exit(2);
		}
	}

	/**
	 * Prints map item used for debugging
	 * 
	 * @param map
	 */
	public static void printMap(TreeMap<String, Event> map) {
		System.out.println("Events: ");
		for (String date : map.keySet()) {
			Event v = map.get(date);
			System.out.println(date + " -->  " + v.getStartTime().getTimeValue());
		}
		System.out.println();
	}

	/**
	 * Adds a comma to the end of a string.
	 * 
	 * @param line
	 * @return
	 */
	String addComma(String line) {
		return line + ",";
	}

	/**
	 * Returns the data in the specified column.
	 * 
	 * @param line   - text from the CSV file
	 * @param column - Column to return
	 * @return String of desired data
	 */
	private String getData(String line, int column) {
		int[] size = getElementsSize(line); // size of each column
		int pos = 0; // initial position to return
		for (int i = 0; i < column; i++) { // Adds the size of the prior columns to initial position
			pos += size[i];
		}
		pos = pos + column; // adds the extra commas along the string.
		String data = line.substring(pos, pos + size[column]); // trims the string.
		return data;
	}

	/**
	 * Finds the size of all the elements in the string. Finds the commas. Every
	 * time it finds a comma it deletes the beginning of the StringBuffer every
	 * time. ==========make sure parameter String line has ending ","=========
	 * 
	 * @param line
	 * @return integer array with size of columns
	 */
	private int[] getElementsSize(String line) {
		StringBuffer text = new StringBuffer(line);
		int[] posComma = new int[numColumns]; // size of columns
		for (int i = 0; i < posComma.length; i++) {
			posComma[i] = text.indexOf(","); // Finds the place where the commas are.
			text.delete(0, posComma[i] + 1); // deletes the beginning of the String up to the comma
		}
		return posComma;
	}



}
