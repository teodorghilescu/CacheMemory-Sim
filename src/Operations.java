
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Teodor
 * class which implements ADD and GET
 */
public class Operations {
	static Premium holder;
	static String nameHolder;
	static int basic;
	
	/**
	 * @param nameObject - the given element
	 * @param memory - type of memory
	 * @return if the element "nameObject" is in the given "memory" or not
	 */
	public static int isInMem(String nameObject, ArrayList<Premium> memory) {
		for(int i = 0;i < memory.size();i++) {
			if(memory.get(i).get().equals(nameObject)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @param scan - iterates through the file
	 * @param mainMemory - the main memory
	 * @param method - LRU/FIFO//LFU method object
	 */
	public static void ADD(Scanner scan, ArrayList<Premium> mainMemory, Cache method) {
		nameHolder = scan.next();
		basic = scan.nextInt();
		if(scan.hasNextInt()) {
			holder = new Premium(nameHolder, basic, scan.nextInt());
		}else {
			holder = new Premium(nameHolder, basic, 0);
		}
		
		int index = isInMem(nameHolder, mainMemory);
		if(index == -1) {
			mainMemory.add(holder);
		}else {
			method.remove(mainMemory.get(index));
			mainMemory.set(index, holder);
		}
	}
	
	/**
	 * 
	 * @param nameObject - object which is being looked for
	 * @param mainMemory - the main memory
	 * @param method - LRU/FIFO//LFU method object
	 * @return - returns what GET must return
	 */
	public static String GET(String nameObject, ArrayList<Premium> mainMemory, Cache method) {
		
		String type;
		int index = isInMem(nameObject, mainMemory);
		if(index == -1) {
			return "2";
		}
		if(method.add(mainMemory.get(index)) != 0) {
			type = mainMemory.get(index).getType();
			mainMemory.get(index).decreaseSub();
			return "1 " + type;
		}
		type = mainMemory.get(index).getType();
		mainMemory.get(index).decreaseSub();
		return "0 " + type;
	}
}
