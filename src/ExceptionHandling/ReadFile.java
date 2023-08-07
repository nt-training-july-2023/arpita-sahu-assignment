package ExceptionHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Path of text file ");
		String path = sc.nextLine();
		BufferedReader reader=null;
		try {
			 reader = new BufferedReader(new FileReader(path));
			String line;
			while((line = reader.readLine()) !=  null)
				System.out.println(line);		
		}catch (IOException e) {
            System.out.println("Error: Unable to read the file.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
         } catch (IOException e) {
                System.out.println("Error: Unable to close the file.");
       }
      }
		sc.close();
	}
  }
