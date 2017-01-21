package com.test.Cassandra;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Class to generate the users.txt file which will be fed into Cassandra to create table entries
public class TextGenerator {
	
	public static void createFile(int size) throws IOException {
		
		BufferedWriter outputFile = null;
		
		File file = new File("users.txt");
		
		outputFile = new BufferedWriter(new FileWriter(file));
		
		for(int i = 1; i < size+1; i++) {
			
			outputFile.write(i + " fName" + i + " lName" + i + " class" + i + "\n");
			
		}
		
		System.out.println("\nWrite Successful!");
		
		outputFile.close();
		
	}
	
}


