package csvHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

import application.Event;
import application.EventTime;

public class CsvHandler {
	String fileName;
	int numColumns = 5;
	int DATE = 0;
	String readError = String.format("The path '%s' is invalid. Refer to 'csvHandler/CSVhandler'.", getPath(fileName));
	
	
	public CsvHandler(String fileName){
		this.fileName = fileName;
		
	}
	
	public TreeMap<String, Event> reader(TreeMap<String, Event> events) {
		File fileIn = new File(getPath(fileName)); //file object
		try (Scanner in = new Scanner(fileIn)) { //try to read the file
			while (in.hasNextLine()) {
				String line = addComma(in.nextLine());//add a comma to the end of the line
				System.out.println(line); //debug
				
				//add elements to the map
				events.put(getData(line, 0), new Event(getData(line, 1), getData(line, 2), getData(line, 0),
						new EventTime(getData(line, 3)), new EventTime(getData(line, 4))));	
			}
		} catch (FileNotFoundException ex1) {
			System.out.println(readError);
			System.exit(1);
		}
//		printMap(events); //debug
		return events;
	}
	public static void printMap(TreeMap<String, Event> map){
		System.out .println("Events: ");
		for(String date: map.keySet()){
		Event v = map.get(date);
		System.out .println(date + " -->  " + v.getStartTime().getTimeValue());
		}
		System.out .println();
		}
	/**
	 * Adds a coma to the end of a string.
	 * @param line
	 * @return
	 */
	String addComma(String line){
		return line + ",";
	}
	
	/**
	 * Returns the data in the specified column.
	 * @param line - text from the CSV file
	 * @param column - Column to return
	 * @return String of desired data
	 */
	private String getData(String line, int column) {
		int [] size = getElementsSize(line); //size of each column
		int pos = 0; //initial position to return
		for (int i = 0; i < column; i++) { //Adds the size of the prior columns to initial position
			pos += size[i];
		}
		pos = pos + column; //adds the extra commas along the string.
		String data = line.substring(pos, pos + size[column]); //trims the string.
		return data;
	}
	/**
	 * Finds the size of all the elements in the string. Finds the commas.
	 * Every time it finds a comma it deletes the beginning of the StringBuffer every time.
	 * ==========make sure parameter String line has ending ","=========
	 * @param line
	 * @return integer array with size of columns
	 */
	private int[] getElementsSize(String line) {
		StringBuffer text = new StringBuffer(line);
		int [] posComma = new int[numColumns]; //size of columns
		for (int i = 0; i < posComma.length; i++) {
			posComma[i] = text.indexOf(","); //Finds the place where the commas are. 
			text.delete(0, posComma[i]+1); //deletes the beginning of the String up to the comma
		}
		return posComma;
	}
	
	public void writter(TreeMap<String, Event> eventMap) {
		File fileOut = new File(getPath(fileName));
//		File fileIn = new File(path);
//		File file = new File("calendarData/user.csv");
		try(PrintWriter fout = new PrintWriter(fileOut)){
			for(String date: eventMap.keySet()) {
				Event event = eventMap.get(date);
				String line = String.format("%s,%s,%s,%s,%s", date, event.getEventTitle(), event.getEventDesc(), event.getStartTime().getTimeValue(), event.getEndTime().getTimeValue());
				fout.println(line);
//				System.out.println("Writer debug: " + line); //debug
			}
			
		} catch (FileNotFoundException ex2) {
			System.out.println("File unable to write");
			System.exit(2);
		}
	}
	public String getPath(String fileName) {
		return String.format("calendarData/%s.csv", fileName);
	}
	
//	public String getPath(String fileName, boolean tmp) {
//		return String.format("calendarData/%sTMP.csv", fileName);
//	}
	
	public void copyFile(File source, File target) {
		try(Scanner in = new Scanner(source);
				PrintWriter out = new PrintWriter(target)){
			while(in.hasNextLine()) {
				String line = in.nextLine();
				out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println(readError);
			e.printStackTrace();
		}
		
	}
}
