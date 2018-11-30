package csvHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvHandler {
	final String FILE_NAME = "calendarData/userData.csv"; //File path

	public void reader() {
		File fileIn = new File(FILE_NAME); //file object
		try (Scanner in = new Scanner(fileIn)) { //try to read the file
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		} catch (FileNotFoundException ex1) {
			System.out.printf("The path '%s' is invalid. Refer to 'csvHandler/CSVhandler'.", FILE_NAME);
			System.exit(1);
		}
	}
	
	public void writter(String eventData) {
		File fileOut = new File("calendarData/userData1.csv");
		File fileIn = new File(FILE_NAME);
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
