package ACTBS;
public class TravelLocation {
	
	private String name; 
	
	public TravelLocation(String name) {
		if(name.length() != 3) {
			throw new IllegalArgumentException("Travel Location name length not 3"); 
		} else if(!name.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Travel Location cannot contain Numbers or symbols"); 
		}
		
		this.name = name;
	}
	
	String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "Travel Location name: " + this.name;
	}
}
