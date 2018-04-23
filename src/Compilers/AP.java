package Compilers;

import java_cup.runtime.Symbol;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class AP {

	public static void main(String [] args) {
		
		String inFile = System.getProperty("user.dir")+"/Python-Compiler/src/Compilers/in.in";
		if (args.length > 1) {
			inFile = args[0];
		}
		
		try {
			FileInputStream fis = new FileInputStream(inFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);
	
			parser parser = new parser(new Lexer(dis));
			Symbol res = parser.parse();
			boolean value = ((Boolean)res.value).booleanValue();

			if(value)
				System.out.println("File: " + inFile + " parsed successfully.");
			else
				System.out.println("Error in parsing file: " + inFile);
	
			fis.close();
			bis.close();
			dis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
