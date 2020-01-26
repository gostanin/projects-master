import ACTBS.SystemManagerAirports;
import ACTBS.SystemManagerCruises;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SystemMenu {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        generalMenu(sc);
        sc.close();
    }

    private static void generalMenu(Scanner sc) {
        Client connectionCruises = new Client(new SystemManagerCruises());
        Client connectionAirports = new Client(new SystemManagerAirports());
        boolean flag = true;
        while(flag) {
            System.out.println(
                    "Menu:\n" +
                    "1. Airport system: create airports, airlines, flights, sections, seats.\n" +
                    "2. Port system: create airports, airlines, flights, sections, seats.\n" +
                    "3. Print Airport System current state.\n" +
                    "4. Print Cruise System current state.\n" +
                    "0. Exit.");

            System.out.println("Choose menu item:");
            int menuItemChosen = readMenuItems(sc);

            switch (menuItemChosen) {
                case 1:
                    airportSysMenu(sc, connectionAirports);
                    break;
                case 2:
                    cruiseSysMenu(sc, connectionCruises);
                    break;
                case 3:
                    connectionAirports.displayDetailed();
                    break;
                case 4:
                    connectionCruises.displayDetailed();
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }

    }

    private static int readMenuItems(Scanner sc) {
        boolean flag = true;
        int menuItem = -1;
        while(flag) {
            try {
                menuItem = sc.nextInt();
                if (menuItem < 0)
                    throw new IllegalArgumentException("Incorrect menu item: has to be positive number.");
                flag = false;
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e) {
                System.out.println("Incorrect menu item: has to be a number.");
                sc.nextLine();
            }
        }
        return menuItem;
    }

    private static void airportSysMenu(Scanner sc, Client connectionAirports){
        boolean flag = true;
        while(flag) {
            System.out.println(
                    "Airport System Menu:\n" +
                            "1. Load system from a file.\n" +
                            "2. Create a travel location.\n" +
                            "3. Create an Airline.\n" +
                            "4. Create a flight.\n" +
                            "5. Create a flight section.\n" +
                            "6. Change price of the seat class of the flight.\n" +
                            "7. Find available seats.\n" +
                            "8. Change pric1e of the seat class for specified origin and destination for Airline.\n" +
                            "9. Book a specified seat on a flight.\n" +
                            "10. Book a seat by a preference.\n" +
                            "11. Display airport system.\n" +
                            "12. Save airport system in a file.\n" +
                            "0. Exit to the previous menu.");

            flag = executeMenu(sc, connectionAirports);
        }
    }

    private static void cruiseSysMenu(Scanner sc, Client connectionCruises) {
        boolean flag = true;
        while (flag) {
            System.out.println(
                    "Cruise System Menu:\n" +
                            "1. Load system from a file.\n" +
                            "2. Create a travel location.\n" +
                            "3. Create a Cruise.\n" +
                            "4. Create a ship.\n" +
                            "5. Create a ship section.\n" +
                            "6. Change price of the cabin class of the trip.\n" +
                            "7. Find available cabins.\n" +
                            "8. Change price of the cabin class for specified origin and destination for Cruise.\n" +
                            "9. Book a specified cabin on a trip.\n" +
                            "10. Book a cabin by a preference.\n" +
                            "11. Display Cruise system.\n" +
                            "12. Save cruise system in a file.\n" +
                            "0. Exit ot the previous menu.");

            flag = executeMenu(sc, connectionCruises);
        }
    }

    private static boolean executeMenu(Scanner sc, Client connection) {

            System.out.println("Choose menu item:");
            int menuItemChosen = readMenuItems(sc);

            switch (menuItemChosen) {
                case 1:
                    connection.loadFromFile(sc);
                    break;
                case 2:
                    connection.createTravelLocation(sc);
                    break;
                case 3:
                    connection.createTravelCompany(sc);
                    break;
                case 4:
                    connection.createTravelType(sc);
                    break;
                case 5:
                    connection.createTravelSection(sc);
                    break;
                case 6:
                    connection.changePriceSpecificTrip(sc);
                    break;
                case 7:
                    connection.findAvailableTrips(sc);
                    break;
                case 8:
                     connection.changePriceOrigDestTrip(sc);
                    break;
                case 9:
                     connection.bookSpotSpecific(sc);
                    break;
                case 10:
                    connection.bookSpotPreference(sc);
                    break;
                case 11:
                    connection.displaySystemDetails();
                    break;
                case 12:
                    connection.saveToFile(sc);
                    break;
                case 0:
                    return false;
            }
            return true;
        }

}

