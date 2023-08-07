package ExceptionHandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CopyFileException {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the path of source file");
		String srcPath = sc.nextLine();
		System.out.print("Enter the path of the destination file: ");
		String destPath = sc.nextLine();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(srcPath));
			BufferedWriter writer = new BufferedWriter(new FileWriter(destPath));
			String line;
			while ((line = reader.readLine()) != null)
				writer.write(line);
			System.out.println("File copied successfully !");
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error : File Not Found");
		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
		}
		sc.close();
	}
}
