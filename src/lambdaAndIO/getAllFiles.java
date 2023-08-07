package lambdaAndIO;

import java.io.File;
import java.io.IOException;
public class getAllFiles{
   public static void main(String args[]) throws IOException {
      File directoryPath = new File("C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src");
      //List of all files and directories
      File filesList[] = directoryPath.listFiles();
      System.out.println("List of files and directories:");
      for(File file : filesList) {
         System.out.println("File name: "+file.getName());
      }
   }
}
