package lambdaAndIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferStream {

	public static void main(String[] args) throws IOException {
		try {
			BufferedInputStream buffIn = new BufferedInputStream( new FileInputStream("C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src\\lambdaAndIO\\file.txt"));
			BufferedOutputStream buffOut = new BufferedOutputStream( new FileOutputStream("C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src\\lambdaAndIO\\file.txt",true));
			int ch ;
			while((ch = buffIn.read()) != -1) {
				System.out.println((char)ch);
				
			} 
			buffIn.close();
			buffOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e);
		} 
				
	}

}
