package ACTBS;


public class Flight extends TravelType{
	
	public Flight(String origin, String destination, int year, int day, int month, int hour, int min, String fID) {
		super(origin, destination, year, day, month, hour, min, fID); 
		
		if(destination.equals(origin)) {
			throw new IllegalArgumentException("Flight " + fID + " not created: Origin and Destination are the same"); 
		}

	}
	
	

	
	
	
	
	
	
}
