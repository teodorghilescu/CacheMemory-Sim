
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Teodor
 * class which implements the FIFO method
 */
public class FIFOCache implements Cache{

	private int maxCache;
	private Queue<Premium> cacheMemory;
	
	public FIFOCache(int cache) {
		this.cacheMemory = new LinkedList<Premium>();
		this.maxCache = cache;
	}
	
	/**
	 * 
	 * @param nameObject - the object method must find
	 * @param memory - the cache memory
	 * @return if the object is in Cache memory
	 */
	public static int isInMem(String nameObject, Queue<Premium> memory) {
		Queue<Premium> copy = new LinkedList<Premium>(memory);
		int index = 0;
		while(!copy.isEmpty()) {
			if(copy.peek().get().equals(nameObject)) {
				return index;
			}
			copy.remove();
			index++;
		}
		return -1;
	}
	/**
	 * removes the object from cache
	 */
	@Override
	public void remove(Premium holder) {
		int index = isInMem(holder.get(), cacheMemory);
		if(index != -1) {
			cacheMemory.remove(holder);
		}
	}

	/**
	 * adds element into cache
	 */
	@Override
	public int add(Premium holder) {
		if(isInMem(holder.get(), cacheMemory) != -1) {
			return 0;
		}
		if(cacheMemory.size() == maxCache) {
			cacheMemory.remove();
		}
		cacheMemory.add(holder);
		return 1;
	}
	
	public String toString() {
		return cacheMemory.toString();
	}
}
