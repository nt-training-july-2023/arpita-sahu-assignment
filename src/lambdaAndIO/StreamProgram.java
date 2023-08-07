package lambdaAndIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamProgram {

	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream(
				"C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src\\lambdaAndIO\\file.txt");
		FileOutputStream outStream = new FileOutputStream(
				"C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src\\lambdaAndIO\\file.txt", true);
		//By using the append mode (new FileOutputStream("output_file.txt", true)), we preserve the existing content in the output file and only add the new content at the end.
		try {
			int ch;
			while ((ch = inputStream.read()) != -1) {
				System.out.print((char) ch);
			}
		} finally {
			inputStream.close();
			outStream.close();
		}
	}

}
