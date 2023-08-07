package lambdaAndIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CopyFile {
	public static void main(String[] args) throws IOException {
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader("C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src\\lambdaAndIO\\oneFile.txt"));
			PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src\\lambdaAndIO\\otherFile.txt"));
			String line;
			while((line = buffReader.readLine()) != null)
				writer.println(line);
			System.out.println("File copied successfully!");
			buffReader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}

}
