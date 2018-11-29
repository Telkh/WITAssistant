package csvHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVreader {
	final String FILE_NAME = "calendarData/userData.csv";

	public void reader() {
		File fileIn = new File(FILE_NAME);
		try (Scanner in = new Scanner(fileIn)) {
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		} catch (FileNotFoundException ex1) {
			System.out.printf("The path '%s' is invalid. Refer to 'csvHandler/CSVhandler'.", FILE_NAME);
		}
	}
}
