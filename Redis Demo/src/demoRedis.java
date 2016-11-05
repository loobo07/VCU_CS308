import redis.clients.jedis.Jedis;
import java.io.*;
import java.util.Scanner;

public class demoRedis extends textGenerator {

	private static int size;
	private static String key;
	private static String value;
	private static String line;
	private static String[]splitLine;

	public demoRedis(int size) {
		
		super(size);
		
	}

	public static void main(String[]args) throws Exception, FileNotFoundException {
		
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("localhost");
		
		//Checks to see if there is an existing connection to Redis servers
		if(jedis.ping() != null) {
			
			System.out.println("Server Ping... " + jedis.ping() + "!");
			System.out.println("Connection Successful!\n");
			
		} 

		Scanner in = new Scanner(System.in);
		
		System.out.println("How many entries will the file be? ");
			textGenerator.setSize(in.nextInt());
			
			size = textGenerator.getSize();
			
				textGenerator.createFile(size);
		
		try {
			
			//Opens text file and stores the two columns into key/value variables
			//and then pushes them to the Redis server
			File file = new File("sample.txt");
			Scanner scanFile = new Scanner(file);
			
			while(scanFile.hasNextLine()) {
				
				line = scanFile.nextLine();
				splitLine = line.split(" ");
				
				for(int i = 1; i < size+1; i++) {
					
					key = splitLine[0];
					value = splitLine[1];
					
					jedis.set(key, value);
					
				}
				
				System.out.println(splitLine[0] + " " + splitLine[1]);
				
			}
			
			System.out.println();
			
			scanFile.close();
			
		} catch(Exception e) {
			
			System.out.println(e);
			
		}
		
		in.close();
		
	}
	
}
