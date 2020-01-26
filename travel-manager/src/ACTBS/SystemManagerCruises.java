package ACTBS;

public class SystemManagerCruises extends SystemManager {

    @Override
    public void createTravelType(String aname, String orig, String dest, int year, int month, int hour, int min, int day, String ID) {
        try {
            super.createTravelType( aname.toUpperCase(),  orig.toUpperCase(),  dest.toUpperCase(),  year,  month,  hour,  min, day, ID.toUpperCase());

            boolean travelLocationCheck = travelLocationCheck(orig, dest);
            
            if(!travelLocationCheck)
            	System.out.println("Cruise " + ID + " not created: Either Origin (" + orig + "), Destination (" + dest +"), or both are invalid Travel Locations");
            else {
	            boolean created = false;
	            for(TravelCompany i: this.travelCompanies) {
	                if(aname.equals(i.getName())) {
	                    System.out.println("Cruise " + ID + " created");
	                    i.addTravelType(orig.toUpperCase(), dest.toUpperCase(), year, month, day, hour, min, ID.toUpperCase(), TransportationType.SHIP);
	                    created = true;
	                }
	            }
	            if(!created)
	                System.out.println("Cruise " + ID + " not created: TravelCompany " + aname + " does not exist!");
	        }
        }
        catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
