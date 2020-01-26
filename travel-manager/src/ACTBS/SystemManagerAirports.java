package ACTBS;

public class SystemManagerAirports extends SystemManager{

    @Override
    public void createTravelType(String aname, String orig, String dest, int year, int month, int hour, int min, int day, String fID) {
        try {
            super.createTravelType( aname.toUpperCase(),  orig.toUpperCase(),  dest.toUpperCase(),  year,  month,  hour,  min, day, fID.toUpperCase());

            boolean travelLocationCheck = travelLocationCheck(orig, dest); 
            
            if(!travelLocationCheck)
            	System.out.println("Flight " + fID + " not created: Either Origin (" + orig + "), Destination (" + dest +"), or both are invalid Travel Locations");
            else {
	            boolean travelCompanyCheck = false;
	            for(TravelCompany i: this.travelCompanies) {
	                if(aname.toUpperCase().equals(i.getName())) {
	                    travelCompanyCheck = true;
	                    if(i.addTravelType(orig.toUpperCase(), dest.toUpperCase(), year, month, day, hour, min, fID.toUpperCase(), TransportationType.FLIGHT)){
	                    	System.out.println("Flight " + fID + " created");
	                    }
	                }
	            }
	            
	            if(!travelCompanyCheck)
	                System.out.println("Flight " + fID + " not created: TravelCompany " + aname + " does not exist!");
	        
            }
            
          }
        catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
