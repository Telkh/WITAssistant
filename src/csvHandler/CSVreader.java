package csvHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVreader {
<<<<<<< HEAD
	final String FILE_NAME = "calendarData/userData.csv";

	public void reader() {
		File fileIn = new File(FILE_NAME);
		try (Scanner in = new Scanner(fileIn)) {
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		} catch (FileNotFoundException ex1) {
			System.out.printf("The path '%s' is invalid. Refer to 'csvHandler/CSVhandler'.", FILE_NAME);
=======
	String fileName = "test.txt";

	public void reader() {
		File fileIn = new File(fileName);
		try (Scanner in = new Scanner(fileIn)) {
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		} catch (FileNotFoundException ex1) {
			System.out.println("File not found");
>>>>>>> branch 'master' of https://github.com/Telkh/WITAssistant.git
			System.exit(5);
		}
	}
}
