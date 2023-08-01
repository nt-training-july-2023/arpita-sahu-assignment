package oops;

import java.io.FileReader;
import java.util.Properties;

public class PropertyClass {
		public static void main(String[] args)throws Exception{  
		    FileReader reader=new FileReader("C:\\Users\\Arpita Sahu\\eclipse-workspace\\NucleusTeq\\src\\oops\\system.properties");
		    Properties p=new Properties();  
		    p.load(reader);  
		      
		    System.out.println(p.getProperty("user"));  
		    System.out.println(p.getProperty("password"));  
		}  
	
	}

