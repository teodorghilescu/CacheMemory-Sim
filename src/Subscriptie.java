
/**
 * 
 * @author Teodor
 * the parent class of all subscriptions
 * contains the name of the subscriber
 */
public abstract class Subscriptie {
	private String name;
	
	public String get() {
		return this.name;
	}
	
	public void set(String name) {
		this.name = name;
	}
}
