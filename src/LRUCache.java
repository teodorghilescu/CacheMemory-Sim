
import java.util.ArrayList;

/**
 * 
 * @author Teodor
 *	class which implements LRU method
 */
public class LRUCache implements Cache{
	
	private int maxCache;
	private static int currentRank;
	private ArrayList<Premium> cacheMemory;
	
	public LRUCache(int cache) {
		this.cacheMemory = new ArrayList<Premium>(cache);
		this.maxCache = cache;
		currentRank = 0;
	}
	
	/**
	 * 
	 * @return the index which is LRU
	 */
	public int indexOfLRU() {
		int min = cacheMemory.get(0).rank;
		int minIndex = 0;
		for(int i = 1;i < cacheMemory.size();i++) {
			if(cacheMemory.get(i).rank <= min) {
				min = cacheMemory.get(i).rank;
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	/**
	 * removes the element from the cache
	 */
	@Override
	public void remove(Premium holder) {
		
		int index = Operations.isInMem(holder.get(), cacheMemory);
		if(index != -1) {
			cacheMemory.remove(holder);
		}
	}

	/**
	 * adds element into cache
	 */
	@Override
	public int add(Premium holder) {
		if(Operations.isInMem(holder.get(), cacheMemory) != -1) {
			holder.rank = currentRank;
			currentRank++;
			return 0;
		}
		
		holder.rank = currentRank;
		currentRank++;
		
		if(cacheMemory.size() == maxCache) {
			System.out.println(holder.get() + " " + holder.rank + " in cache " + cacheMemory.get(indexOfLRU()).get() + " of " +  cacheMemory.get(indexOfLRU()).rank);
			cacheMemory.set(indexOfLRU(), holder);
		}else {
			cacheMemory.add(holder);
		}
		return 1;
	}
}
