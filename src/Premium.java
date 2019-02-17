
/**
 * 
 * @author Teodor
 *	the Premium Subscription class
 */
public class Premium extends Basic{
	Integer premiumRequests;
	int rank;
	
	public Premium(String name, int basic, int premium) {
		super.set(name);
		super.basicRequests = basic;
		this.premiumRequests = premium;
		this.rank = 0;
	}
	
	/**
	 * gets the name
	 */
	@Override
	public String get() {
		return super.get();
	}
	
	/**
	 * 
	 * @return the type of subscription
	 */
	public String getType() {
		if(!this.premiumRequests.equals(0)) {
			return "Premium";
		}
		if(!super.basicRequests.equals(0)) {
			return "Basic";
		}
		return "Free";
	}
	
	/**
	 * decreases the number of subs
	 */
	public void decreaseSub() {
		if(!this.premiumRequests.equals(0)) {
			this.premiumRequests--;
			return;
		}
		if(!super.basicRequests.equals(0)) {
			super.basicRequests--;
		}
	}
}
