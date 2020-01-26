# Travel Manager project

TravelManager is a program meant to facilitate the creation of Transportation Systems, and in our
case Airline and Cruise Systems. Our program is open to the addition of various other methods or Transportation.

##Usage

In order to use our program, simply run it in your terminal or however you prefer, and the SystemMenu will give you some options:

```bash
Menu:
1. Airport system: create airports, airlines, flights, sections, seats.
2. Port system: create airports, airlines, flights, sections, seats.
3. Print Airport System current state.
4. Print Cruise System current state.
0. Exit.
Choose menu item:

```
From this menu, you can get into two different sub-menu's. By inputting 1 you will be taken to the Airport System Menu, and by inputting 2 you will be taken to the Cruise System Menu.

If you choose 3 or 4 on this menu, you will be given information on existing the Airport System or the existing Cruise System. This information will include Travel Locations (Airports/Ports), Travel Companies (Airlines/Cruiselines), Travel Types (Flights/Ships), Travel Type Sections, and Spots (aka seats) in said sections. Here's an example of what the output from an Airport System looks like:

```bash
-------------------------------------System Detailed---------------------------------------
Travel locations:
| DEN | | NYC | | SEA | | LAX | 

AMER
AA1        DEN   LAX   10/8/2019  16:30     
ECONOMY:  
   1A   1B   1C
   2A   2B   2C
   3A   3B   3C
   4A   4B   4C

FIRST:    
   1A   1B   1C
   2A   2B   2C

AA2        LAX   DEN   8/9/2019   7:30      
ECONOMY:  
   1A   1B   1C
   2A   2B   2C
   3A   3B   3C
   4A   4B   4C
   5A   5B   5C

FIRST:    
   1A   1B   1C
   2A   2B   2C
   3A   3B   3C

AA3        DEN   LAX   10/8/2019  16:30     
ECONOMY:  
   1A   1B   1C
   2A   2B   2C
   3A   3B   3C
   4A   4B   4C

UNTD
UA21       NYC   SEA   11/8/2019  12:30     
ECONOMY:  
   1A   1B   1C
   2A   2B   2C
   3A   3B   3C
   4A   4B   4C
   5A   5B   5C
   6A   6B   6C

FIRST:    
   1A   1B   1C
   2A   2B   2C
   3A   3B   3C

UA12       SEA   DEN   8/9/2019   7:30      
BUSINESS: 
   1A   1B   1C
   2A   2B   2C
   3A   3B   3C
   4A   4B   4C
   5A   5B   5C

FIRST:    
   1A   1B   1C
   2A   2B   2C

-------------------------------------------------------------------------------------------


```

If we go into Menu option 1 or 2, we will see a very similiar menu's for each: 

```bash
1. Load system from a file.
2. Create a travel location.
3. Create an Airline.
4. Create a flight.
5. Create a flight section.
6. Change price of the seat class of the flight.
7. Find available seats.
8. Change price of the seat class for specified origin and destination for Airline.
9. Book a specified seat on a flight.
10. Book a seat by a preference.
11. Display airport system.
12. Save airport system in a file.
0. Exit to the previous menu.
Choose menu item:
```
Here is a run down of what these options

1: Prompts for a file name in order to load up a system into the program. If the file is formatted correctly it will create the system and you will be able to interact with it. 

```bash
Load from file.
Enter the path to the file or type 0 to exit:
SampleInput.txt
```

2: This option will create a travel location and add it to the System.  It will prompt you for a Name which must be 3 characters long. 
Example:

```bash
Create location.
Enter location abbreviation(ex: LON):
NYC
```

3: This option will create an Travel Company (Either Airline or Cruise) and add it to the System. It will also prompt you for a Name, which can be no longer than 6 characters and must be longer than 1 character.
Example:

 ```bash
Create Company.
Enter the name of the company(ex: DELTA):
AMER
```

4: This option will create a Travel Type (Either flight or ship) and add it to the system. You will need to provide some fields to create this object.
Example:

```bash
Create a flight.
Enter company name(ex: DELTA):
DELTA
Enter travel ID number(ex: DL546):
DL546
Enter origin location(ex: LON):
LON
Enter destination location(ex: LON):
TEX
Enter departure year(ex: 2019):
2019
Enter departure month(ex: 10):
10
Enter departure day(ex: 25):
22
Enter departure hour(ex: 7):
3
Enter departure min(ex: 45):
30
```

5: This option allows you to create a Section for a Travel Type(flight or ship). This Section will have a Layout (Small, Medium, or Wide) a SeatClass (Business, First, or Economy), as well as a few other fields that you will provide. 
Example:

```bash
Create a section.
Enter company name(ex: DELTA):
DELTA
Enter travel ID number(ex: DL546):
DL546
Choose seat layout:
1. Small - creates 3 column layout with aisle between 1st and 2nd.
2. Medium - creates 4 column layout with aisle between 2nd and 3rd.
3. Wide - creates 10 column layout with aisles between 3rd, 4th and 7th, 8th.
1
Choose seat class:
1. Business
2. First
3. Economy
1
Enter number of rows in the class(ex: 10):
3
Enter price: all seats have same price in the class(ex: 200):
300
```

6: This option allows you to change the price of a specified seat class on a specific Travel Type (flight or ship). You will provide some input so that the system knows what TravelCompany/TravelType/Section you want to change the price of, then it will ask you for a new price. 


```bash
Change price.
Enter name of the company(ex: DELTA):
DELTA
Enter travel number(ex: DL546):
DL546
Choose seat class:
1. Business
2. First
3. Economy
2
Enter new price(ex: 200):
400
```

7: This option will return all available seats on a TravelType (flight/ship) given specific information about said TravelType. 
Example

```bash
Find available seats
Enter origin(ex: GEG):
SEA
Enter destination(ex: GEG):
DEN
Choose seat class:
1. Business
2. First
3. Economy
1
Enter year(ex: 2019):
2019
Enter month(ex: 10:
8
Enter day(ex: 25)
9
--------Available ----------
-----------------------------------
TravelCompany: UNTD
Origin: SEA
Destination: DEN
Date: 8/9/2019

	BUSINESS class: (Price: 700.0)
	1A 1B 1C 
	2A 2B 2C 
	3A 3B 3C 
	4A 4B 4C 
	5A 5B 5C 


	FIRST class: (Price: 1200.0)
	1A 1B 1C 
	2A 2B 2C 

-----------------------------------
```

8: This option will change the price of a Section on all TravelTypes for a company with a specified origin/destination combination. You provide relevant information on what TravelType to change as well as the new price you want the Section to have. 
Example:

```bash
Change price(origin, destination).
Enter name of the company(ex: DELTA):
UNTD
Enter origin(ex: LAX):
SEA
Enter destination(ex: LAX):
DEN
Choose seat class:
1. Business
2. First
3. Economy
3
Enter new price(ex: 200):
300
Price of ECONOMY class for all travels from SEA to DEN in UNTD company has been changed to 300.00

```
9: This option allows you to book a specified seat on a TravelType. You must provide the Travel Number (ID) of the TravelType as well as other relevant information in order to book the specified seat. 
Example: 

```bash
Book a seat
Enter name of the company(ex: DELTA):
UNTD
Enter travel number(ex: DL546):
UA12
Choose seat class:
1. Business
2. First
3. Economy
3
Enter row number(ex: 10):
2
Enter seat letter(ex: B):
C
```

10: This option will book a seat on a TravelType (Flight/Ship) based on Window/Aisle preference. You must give info about TravelType to attempt booking. 
Example:

```bash
Choose menu item:
10
Book a seat
Enter name of the company(ex: DELTA):
UNTD
Enter travel number(ex: DL546):
UA12
Choose seat class:
1. Business
2. First
3. Economy
2
Choose seat class:
1. Window
2. Aisle
2
```
11: This option will display information about the System as it appear so far. 
Example: 

```bash
------------------------------------------System-------------------------------------------
Travel locations:
| DEN | | NYC | | SEA | | LAX | 

Company    Flight number        From       To         Date                 Departure time      
AMER       AA1                  DEN        LAX        10/8/2019            16:30               
AMER       AA2                  LAX        DEN        8/9/2019             7:30                
AMER       AA3                  DEN        LAX        10/8/2019            16:30               
UNTD       UA21                 NYC        SEA        11/8/2019            12:30               
UNTD       UA12                 SEA        DEN        8/9/2019             7:30                
-------------------------------------------------------------------------------------------

```
12: This option saves the airport system in a file using the same format for option 1 (Load System from a File). You specify the file name to write to. If it's already created, the previous system will be overwritten for the current state. If the file doesn't exist, the program will create it, write to it, and store it in the root directory. 
Example:

```bash
Save to file.
Enter the path to the file or type 0 to exit:
Output.txt
```


## Miscellaneous

1: If incorrect input is provided when creating objects, etc. a message will be printed describing the problem. In some cases, and exception will be thrown, but this will always be caught. 

2: Any input file provided must be in the same exact format as the file "SampleInput.txt" provided in the root directory of this program. Otherwise, the system will not be created and creation will stop wherever the error is in the input file. 

3: Ships will have only one Origin and one Destination just like Flights

4: The one way prices for an airline are not required to all be the same. You are able, however, to change the Section Price on all Travel Types for a specified Travel Company.

5: We included two JUnit tests, one for the Section's and one for the Spot's. They are not fully comprehensive bu they do test some things. We wanted to add more tests but did not have enough time.

6: We implemented 7 custom exceptions for the program. These can be found under ACTBS.SystemExceptions. Again, wanted to create more but ran out of time. 
  
