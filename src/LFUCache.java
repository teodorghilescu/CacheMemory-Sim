
import java.util.ArrayList;

/**
 * 
 * @author Teodor
 * class which implements LFU method
 */
public class LFUCache implements Cache{
	
	private int maxCache;
	private ArrayList<Premium> cacheMemory;
	
	public LFUCache(int cache) {
		this.cacheMemory = new ArrayList<Premium>(cache);
		this.maxCache = cache;
	}

	/**
	 * 
	 * @return the element which is LFU
	 */
	public int indexOfLFU() {
		int min = cacheMemory.get(0).rank;
		int minIndex = 0;
		for(int i = 1;i < cacheMemory.size();i++) {
			if(cacheMemory.get(i).rank <= min) {
				min = cacheMemory.get(i).rank;
				minIndex = i;
			}
		}
		for(int i = 0;i < cacheMemory.size();i++) {
			if(cacheMemory.get(i).rank == min) {
				return i;
			}
		}
		return minIndex;
	}
	
	/**
	 * removes the element from cache
	 */
	@Override
	public void remove(Premium holder) {
		
		int index = Operations.isInMem(holder.get(), cacheMemory);
		if(index != -1) {
			holder.rank = 0;
			cacheMemory.remove(holder);
		}
	}

	/**
	 * adds element into cache
	 */
	@Override
	public int add(Premium holder) {
		if(Operations.isInMem(holder.get(), cacheMemory) != -1) {
			holder.rank++;
			return 0;
		}
		
		if(cacheMemory.size() == maxCache) {
			cacheMemory.get(indexOfLFU()).rank = 0;
			cacheMemory.remove(indexOfLFU());
			cacheMemory.add(holder);
		}else {
			cacheMemory.add(holder);
		}
		return 1;
	}
}
