package csvHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import application.Event;
import application.EventTime;

public class CsvHandler {
	String fileName;
	String path;
	int numColumns = 5;
	int DATE = 0;
	
	
	public CsvHandler(String fileName){
		this.fileName = fileName;
		path = "calendarData/" + this.fileName + ".csv";
	}
	
	public void reader() {
		File fileIn = new File(path); //file object
		HashMap<String, Event> events = new HashMap<>(); //creates events map
		try (Scanner in = new Scanner(fileIn)) { //try to read the file
			while (in.hasNextLine()) {
				
				String line = addComma(in.nextLine());
				System.out.println(line);
				events.put(getData(line, DATE), new Event(getData(line, 1), getData(line, 2), getData(line, 0),
						new EventTime(Integer.parseInt(getData(line, 3).substring(0, 2)), Integer.parseInt(getData(line, 3).substring(3, getData(line, 3).length()))) ,
						new EventTime(Integer.parseInt(getData(line, 3).substring(0, 2)), Integer.parseInt(getData(line, 4).substring(3, getData(line, 4).length())))));
			}
		} catch (FileNotFoundException ex1) {
			System.out.printf("The path '%s' is invalid. Refer to 'csvHandler/CSVhandler'.", path);
			System.exit(1);
		}
		printMap(events);
	}
	public static void printMap(HashMap<String, Event> map){
		System.out .println("Events: ");
		for(String date: map.keySet()){
		Event v = map.get(date);
		System.out .println(date + " --> " + v.getStartTime() + " feet.");
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
	
	public void writter(String eventData) {
		File fileOut = new File("calendarData/userData1.csv");
		File fileIn = new File(path);
		File file = new File("calendarData/user.csv");
		try(Scanner in = new Scanner(fileIn);
				PrintWriter fout = new PrintWriter(fileOut)){
			while (in.hasNextLine()) {
				String a = in.nextLine();
				System.out.println(a);
				fout.println(a);
			}
			
			fout.println(eventData);
			
			file.delete();
			
		} catch (FileNotFoundException ex2) {
			System.out.println("File unable to write");
			System.exit(2);
		}
	}
}
