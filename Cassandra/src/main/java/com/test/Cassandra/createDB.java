package com.test.Cassandra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class createDB extends TextGenerator {

	private static String userID;
	private static String firstName;
	private static String lastName;
	private static String someClass;
	
	public createDB() {
		
		super();
		
	}

	//Creates a table in the specified keyspace
	public static String createTable(String keyspaceName, String tableName) {
		
		return "CREATE TABLE " + keyspaceName + "." + tableName + " ("
				+ "userID text PRIMARY KEY,"
				+ " first_name text,"
				+ " last_name text,"
				+ " class text);";
		
	}
	
	//Helper method to make the insert operation easier to handle
	public static String insertInto(String keyspaceName, String tableName) {
		
		return "INSERT INTO " + keyspaceName + "." + tableName + "(userID, first_name, last_name, class)"
				+ " VALUES ";
		
	}

	public static void main(String[] args) {

		Cluster cluster = null;
		Scanner in = new Scanner(System.in);
				
		try { 
			
			//Change the value in createFile to whichever value
			//the teseter needs to test.
			TextGenerator.createFile(50);
			
			//Builds Cassandra cluster and adds the home IP address 
			//to it's destination.
			cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
			
			//Begins new session and connects cluster to the demo 
			//keyspace, and then uses it.
			Session session = cluster.connect("demo");
			session.execute("USE demo");
			
			//Executes the createTable method to create the sample 
			//table.
			session.execute(createTable("demo", "users"));

			//Creates file and reader to read in users.txt context
			File file = new File("users.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			//Represents one line of input read from file.
			String line;
			
			//Loops to insert each line of users.txt into users table
			while((line = reader.readLine()) != null) {
				
				String[]parts = line.split(" ", 4);
				
				if(parts.length == 4) {
					
					userID = parts[0];
					firstName = parts[1];
					lastName = parts[2];
					someClass = parts[3];
					
					session.execute(insertInto("demo", "users") +
							"('"+userID+"','"+firstName+"','"+lastName+"','"+someClass+"');");
					
				} else {
					
					System.out.println("Ignoring line " + line);
					
				}
					
			}
			
			reader.close();
			
			//Displays each row to Java console for confirmation of storage.
			ResultSet allFields = session.execute("select * from demo.users");
			List<Row> allRows = allFields.all();
			
			for(Row row : allRows) {
				
				System.out.println(row.toString());
				
			}
			
			//Deletes the users table from the demo keyspace if the 
			//tester does not wish to save it (deletion is recommended).
			String drop = "";
			System.out.println("\nDelete the table?");
				drop = in.next();
			
			if(drop.equalsIgnoreCase("no")) {
				
				System.out.println("Table saved.");
				return;
				
			} else {
				
				session.execute("DROP TABLE users");
				System.out.println("Table deleted.");
				
			}
									
		} 
		
		catch(Exception e) {
			
			System.out.println("Something went wrong.");
			e.printStackTrace(System.out);
			
		} 
		
		finally {
			
			if(cluster != null) {
				
				cluster.close();
				in.close();
				
			}
			
		}
		
	}
	
}
