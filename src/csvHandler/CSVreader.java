package csvHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVreader {
	String fileName = "test.txt";

	public void reader() {
		File fileIn = new File(fileName);
		try (Scanner in = new Scanner(fileIn)) {
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		} catch (FileNotFoundException ex1) {
			System.out.println("File not found");
			System.exit(5);
		}
	}
}
