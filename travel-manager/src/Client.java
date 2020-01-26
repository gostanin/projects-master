import ACTBS.*;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    private SystemManager manager;

    public Client(SystemManager manager) {
        this.manager = manager;
    }

    public void displaySystemDetails() {
        manager.displaySystemDetails();
    }

    public void displayDetailed() {
        manager.displayDetailedSystem();
    }

    public void loadFromFile(Scanner sc) {
        System.out.println("Load from file.");
        String filePath;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                filePath = getString(sc, "Enter the path to the file or type 0 to exit:");
                if (filePath.equalsIgnoreCase("0"))
                    stop = true;
                else {
                    if(!new File(filePath).exists())
                        System.out.println("File does not exist");
                    else {
                        manager.loadInputFile(filePath);
                        stop = true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Path has to be a string");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void changePriceSpecificTrip(Scanner sc) {
        System.out.println("Change price.");
        String company;
        SeatClass seatClass;
        String travelID;
        double price;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                company = getString(sc, "Enter name of the company(ex: DELTA):");
                travelID = getString(sc, "Enter travel number(ex: DL546):");

                seatClass = getSeatClass(sc);

                price = getPrice(sc);

                manager.changeSpotPriceBySection(company,travelID, seatClass, price);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void findAvailableTrips(Scanner sc) {
        if(manager instanceof SystemManagerCruises)
            System.out.println("Find available cabins.");
        else if(manager instanceof SystemManagerAirports)
            System.out.println("Find available seats");
        String origin;
        String destination;
        int year;
        int month;
        int day;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                origin = getString(sc, "Enter origin(ex: GEG):");
                destination = getString(sc, "Enter destination(ex: GEG):");
                year = getInt(sc, "Enter year(ex: 2019):");
                month = getInt(sc, "Enter month(ex: 10:");
                day = getInt(sc, "Enter day(ex: 25)");

                manager.findAvailableTravels(origin, destination, year, month, day);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void changePriceOrigDestTrip(Scanner sc) {
        System.out.println("Change price(origin, destination).");
        String company;
        String origin;
        String destination;
        SeatClass seatClass;
        double price;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                company = getString(sc, "Enter name of the company(ex: DELTA):");
                origin = getString(sc, "Enter origin(ex: LAX):");
                destination = getString(sc, "Enter destination(ex: LAX):");

                seatClass = getSeatClass(sc);

                price = getPrice(sc);

                manager.changeSpotPriceByOriginDestination(company, seatClass, origin, destination, price);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void bookSpotSpecific(Scanner sc) {
        if(manager instanceof SystemManagerCruises)
            System.out.println("Book a cabin.");
        else if(manager instanceof SystemManagerAirports)
            System.out.println("Book a seat");
        String company;
        SeatClass seatClass;
        String travelID;
        int row;
        char col = ' ';
        boolean correctLetter = false;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                company = getString(sc, "Enter name of the company(ex: DELTA):");
                travelID = getString(sc, "Enter travel number(ex: DL546):");

                seatClass = getSeatClass(sc);
                sc.nextLine();

                row = getInt(sc, "Enter row number(ex: 10):");
                while(!correctLetter) {
                    System.out.println("Enter seat letter(ex: B):");
                    col = sc.next().charAt(0);
                    if(Character.isAlphabetic(col))
                        correctLetter = true;
                    else
                        System.out.println("Has to be a letter");
                }

                manager.bookSpot(company,travelID, seatClass, row, col);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input + " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    public void bookSpotPreference(Scanner sc) {
        if(manager instanceof SystemManagerCruises)
            System.out.println("Book a cabin.");
        else if(manager instanceof SystemManagerAirports)
            System.out.println("Book a seat");
        String company;
        String travelID;
        Position preference;
        SeatClass seatClass;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                company = getString(sc, "Enter name of the company(ex: DELTA):");
                travelID = getString(sc, "Enter travel number(ex: DL546):");

                seatClass = getSeatClass(sc);
                sc.nextLine();
                preference = getPositionType(sc);
                sc.nextLine();



                manager.bookSpotPreference(company, travelID, seatClass, preference);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input + " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveToFile(Scanner sc) {
        System.out.println("Save to file.");
        String filePath;
        FileWriter writer = null;
        boolean saved;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                filePath = getString(sc, "Enter the path to the file or type 0 to exit:");
                if (filePath.equalsIgnoreCase("0"))
                    stop = true;
                else {
                    writer = new FileWriter(filePath);
                }
                saved = manager.saveToFile(writer);
                System.out.println("System has " + ((saved) ? "" : "not ") + "been saved to a file.");
                writer.close();
                stop = true;
            } catch (FileNotFoundException e) {
                System.out.println("File was not found.");
            } catch (InputMismatchException e) {
                System.out.println("Path has to be a string");
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void createTravelCompany(Scanner sc) {
        System.out.println("Create Company.");
        String name;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                name = getString(sc, "Enter the name of the company(ex: DELTA):");
                manager.createTravelCompany(name);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Company name has to be a string");
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void createTravelLocation(Scanner sc) {
        System.out.println("Create location.");
        String name;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                name = getString(sc, "Enter location abbreviation(ex: LON):");
                manager.createTravelLocation(name);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Location has to be a string");
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void createTravelType(Scanner sc) {
        if(manager instanceof SystemManagerAirports)
            System.out.println("Create a flight.");
        else if(manager instanceof SystemManagerCruises)
            System.out.println("Create a ship");
        String airlineName, orig, dest, travelID;
        int year, month, hour, min, day;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                airlineName = getString(sc, "Enter company name(ex: DELTA):");
                travelID = getString(sc, "Enter travel ID number(ex: DL546):");
                orig = getString(sc, "Enter origin location(ex: LON):");
                dest = getString(sc, "Enter destination location(ex: LON):");

                year = getInt(sc, "Enter departure year(ex: 2019):");
                month = getInt(sc, "Enter departure month(ex: 10):");
                day = getInt(sc, "Enter departure day(ex: 25):");
                hour = getInt(sc, "Enter departure hour(ex: 7):");
                min = getInt(sc, "Enter departure min(ex: 45):");

                manager.createTravelType(airlineName,orig,dest,year,month,hour,min,day,travelID);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Location has to be a string");
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void createTravelSection(Scanner sc) {
        System.out.println("Create a section.");
        String airlineName, travelID;
        SeatLayout seatLayout;
        SeatClass seatClass;
        int rows;
        double price = -1;
        sc.nextLine();
        boolean stop = false;
        while(!stop) {
            try {
                airlineName = getString(sc, "Enter company name(ex: DELTA):");
                travelID = getString(sc, "Enter travel ID number(ex: DL546):");
                seatLayout = getSeatLayout(sc);
                seatClass = getSeatClass(sc);
                rows = getInt(sc, "Enter number of rows in the class(ex: 10):");
                if(manager instanceof SystemManagerCruises)
                    price = getPrice(sc, "Enter price: all cabins have same price in the class(ex: 200):");
                else if(manager instanceof SystemManagerAirports)
                    price = getPrice(sc, "Enter price: all seats have same price in the class(ex: 200):");

                manager.createSection(airlineName, travelID, rows, seatLayout, seatClass, price);
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Location has to be a string");
            }catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String getString(Scanner sc, String s) {
        String data;
        System.out.println(s);
        data = sc.nextLine();
        return data;
    }

    private int getInt(Scanner sc, String s) {
        boolean input = true;
        int data = -1;
        while(input) {
            try {
                System.out.println(s);
                data = sc.nextInt();
                input = false;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                sc.nextLine();
            }
        }
        return data;
    }

    private double getPrice(Scanner sc, String s) {
        boolean input = true;
        double data = -1;
        while(input) {
            try {
                System.out.println(s);
                data = sc.nextDouble();
                input = false;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                sc.nextLine();
            }
        }
        return data;
    }

    private Position getPositionType(Scanner sc) {
        boolean input = true;
        Position preference = null;
        int seatClassNum;
        boolean preferenceCheck = false;
        while(input) {
            try {

                System.out.println("Choose seat class:\n" +
                        "1. Window\n" +
                        "2. Aisle");

                while (!preferenceCheck) {
                    seatClassNum = sc.nextInt();
                    if (seatClassNum == 1) {
                        preference = Position.WINDOW;
                        preferenceCheck = true;
                    } else if (seatClassNum == 2) {
                        preference = Position.AISLE;
                        preferenceCheck = true;
                    } else
                        System.out.println("Incorrect number.\nEnter number:");

                }
                input = false;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                sc.nextLine();
            }
        }
        return preference;
    }

    private SeatClass getSeatClass(Scanner sc) {
        boolean input = true;
        SeatClass seatClass = null;
        int seatClassNum;
        boolean seatClassCheck = false;
        while(input) {
            try {

                System.out.println("Choose seat class:\n" +
                        "1. Business\n" +
                        "2. First\n" +
                        "3. Economy");
                while (!seatClassCheck) {
                    seatClassNum = sc.nextInt();
                    if (seatClassNum == 1) {
                        seatClass = SeatClass.BUSINESS;
                        seatClassCheck = true;
                    } else if (seatClassNum == 2) {
                        seatClass = SeatClass.FIRST;
                        seatClassCheck = true;
                    } else if (seatClassNum == 3) {
                        seatClass = SeatClass.ECONOMY;
                        seatClassCheck = true;
                    } else
                        System.out.println("Incorrect number.\nEnter number:");

                }
                input = false;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                sc.nextLine();
            }
        }
        return seatClass;
    }

    private SeatLayout getSeatLayout(Scanner sc) {
        boolean input = true;
        SeatLayout seatLayout = null;
        int seatLayoutNum;
        boolean seatLayCheck = false;
        while(input) {
            try {

                System.out.println("Choose seat layout:\n" +
                        "1. Small - creates 3 column layout with aisle between 1st and 2nd.\n" +
                        "2. Medium - creates 4 column layout with aisle between 2nd and 3rd.\n" +
                        "3. Wide - creates 10 column layout with aisles between 3rd, 4th and 7th, 8th.");
                while (!seatLayCheck) {
                    seatLayoutNum = sc.nextInt();
                    if (seatLayoutNum == 1) {
                        seatLayout = SeatLayout.SMALL;
                        seatLayCheck= true;
                    } else if (seatLayoutNum == 2) {
                        seatLayout = SeatLayout.MEDIUM;
                        seatLayCheck = true;
                    } else if (seatLayoutNum  == 3) {
                        seatLayout = SeatLayout.WIDE;
                        seatLayCheck = true;
                    } else
                        System.out.println("Incorrect number.\nEnter number:");

                }
                input = false;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                sc.nextLine();
            }
        }
        return seatLayout;
    }

    private double getPrice(Scanner sc) {
        boolean priceCorrect = false;
        double price = -1;
        boolean input = true;
        while(input) {
            try {
                System.out.println("Enter new price(ex: 200):");
                while (!priceCorrect) {
                    price = sc.nextDouble();
                    if (price > 0 && price < 20000)
                        priceCorrect = true;
                    else
                        System.out.println("Price has to be in range(1, 20000)");
                }
                input = false;
            }
            catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                sc.nextLine();
            }
        }
        return price;
    }
}

