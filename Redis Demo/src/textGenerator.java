import java.io.*;

//Class to generate the sample.txt file which will be fed into Redis to create keyspace
public class textGenerator {
	
	private static int size;
	
	public textGenerator(int size) {
		
		size = textGenerator.getSize();
		
	}
	
	public static int getSize() {
		
		return size;
		
	}

	public static void setSize(int size) {
		
		textGenerator.size = size;
		
	}
	
	public static void createFile(int size) throws IOException {
		
		BufferedWriter outputFile = null;
		
		File file = new File("sample.txt");
		
		outputFile = new BufferedWriter(new FileWriter(file));
		
		for(int i = 1; i < size+1; i++) {
			
			outputFile.write("Key" + i + " Item" + i + "\n");
			
		}
		
		System.out.println("Write Successful!");
		
		outputFile.close();
		
	}
	
}

