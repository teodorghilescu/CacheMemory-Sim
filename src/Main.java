
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Teodor
 * the main class
 */
public class Main {
	
	static ArrayList<Premium> mainMemory = new ArrayList<Premium>();
	
	public static void main(String argv[]) throws IOException {
		
		FileReader file = new FileReader(argv[0]);
		FileWriter filew = new FileWriter(argv[1]);
		
		String nameObject;
		Scanner scan = new Scanner(file);
		Cache method = null;
		String nameMethod = scan.nextLine();
		int maxCache = scan.nextInt();
		
		if(nameMethod.equals("LRU")) {
			method = new LRUCache(maxCache);
		}else if(nameMethod.equals("FIFO")) {
			method = new FIFOCache(maxCache);
		}else {
			method = new LFUCache(maxCache);
		}
		
		int nrOperations = scan.nextInt();
		
		for(int i = 0;i < nrOperations;i++) {
			
			if((scan.next().equals("ADD"))){
				Operations.ADD(scan, mainMemory, method);
			}else {
				nameObject = scan.next();
				filew.write(Operations.GET(nameObject, mainMemory, method) + "\n");
			}
		}
		scan.close();
		filew.close();
	}
}
