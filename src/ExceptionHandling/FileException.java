package ExceptionHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileException {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter path of the text file to read: ");
		String filePath = sc.nextLine();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null)
				System.out.println(line);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error : File Not Found ");
		} catch (IOException e) {
			System.out.print("Error : Unable to Read File ");
		}
		sc.close();
	}

}
