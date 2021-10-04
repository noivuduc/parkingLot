# Valet Parking System
## Requirements
Problem Statement
Your task is to design an automated valet car parking system where you manage a parking space
for vehicles as they enter/exit it and manage its revenue. The data provided to you(refer to the
Data Description section below) would include the number of parking lots available and the
entry/exit details of each vehicle.

For the purpose of this question you can assume that Cars and
Motorcycles are the only two types of vehicles.
Each vehicle upon entry can only park in a lot available for that vehicle type. If there are no lots
available for that vehicle type, it should be denied an entry into the space. As we’re building a
valet car park, all the lots in the parking space can be considered as being distinctly numbered
eg: CarLot1, CarLot2,..., MotorcycleLot1, MotorcycleLot2,....

Each vehicle upon entering is allotted
to the lot with the lowest number for that vehicular type eg: a car entering a parking space with
the available lots CarLot2, CarLot4, CarLot5 would be assigned to CarLot2. When a vehicle wants
to exit the car park, the system will return the parking lot that the vehicle will be removed from
and charge them an appropriate parking fee(rounded up to the nearest hour, i.e., 1hr 1min is
charged as 2hr): $1/hour for a motorcycle and $2/hour for a car.
You may use any language/framework for this exercise.
Data Description
Your program accepts an input file as an argument when running. The format of the file is
described below:
```
3 4
Enter motorcycle SGX1234A 1613541902
Enter car SGF9283P 1613541902
Exit SGX1234A 1613545602
Enter car SGP2937F 1613546029
Enter car SDW2111W 1613549730
Enter car SSD9281L 1613549740
Exit SDW2111W 1613559745
```
The first line indicates the number of parking slots that are present in the space for Cars and
Motorcycles respectively in the parking lot.

For each subsequent line, there would be two types of events:
● Vehicle entering the space: Enter <motorcycle|car> <vehicle number>
<timestamp>. The program should print out either Accept or Reject based on the
availability of lots in the parking space. If the vehicle is accepted, the program also returns
the name of the lot being occupied by it.
● Vehicle exiting the space: Exit <vehicle number> <timestamp>. The program
prints out the released lot and the parking fee.
Given the example above, the program output would look like:
Accept MotorcycleLot1
```Accept CarLot1
MotorcycleLot1 2
Accept CarLot2
Accept CarLot3
Reject
CarLot3 6
```
Any erroneous cases should be handled with an appropriate error message.
## Prerequisites
You have to install JDK 11 in order to run the application
- Java 11
## UML Design
![alt text](https://i.ibb.co/Bc0X358/Screenshot-2021-10-04-at-8-01-59-PM.png)

## Project Structure
![alt text](https://i.ibb.co/Cm0jNm0/Screenshot-2021-10-04-at-8-01-15-PM.png)

## Assumptions
- End time is always greater than Start Time.
- The order of the vehicle enter/exit must be ordered by **timestamp**, that means from line 1 to line n, the timestamp must be strictly increase
- There is only one entrance for exit and enter of this parkingLot.
  - The input format must be:
    - For entering: 
    > Enter `<Vehicle type>` `<Vehicle number>` `<timestamp>`
    - For Exit:
    > Exit `<Vehicle number>` `<timestamp>`

## What I have done?
- Read input from file, 
- Base on the action(Enter/Exit), system will perform the respective operation.
- If the parkingLot is full, system will reject that vehicle
- If there is an available slot, system will allocate the slot to the vehicle  with the lowest number and create the ticket.
- When the vehicle exits the parkingLot, system will calculate the price and display to console.
- The ticket will be stored for future use (auditing, promotion,...).
- A simple Logger is implemented.
- Some data structures are used for the case of multiple entrances (Asynchronous).

## What needs to be done in the future?
- Handle the case where ParkingLot has multiple entrances and exits. One exit/entrance equals one Thread.
- The pricing system must be improved (membership, charge for over parked, peak hour charge,...)
- System will be able to handle multiple parking lots, parking lot might have multiple floors.
## Complexity
- **O(log(N))** for the enter operation.
- **O(log(N))** for the exit operation.

## How to run
The application is compressed to executable Jar file, and you can run by using the command below. If you don't specify the input, application will use the default input.

```bash
java -jar parkingLot.jar <path to input file>
```
### The input file format:
*The first line indicates the number of parking slots that are present in the space for Cars and
Motorcycles respectively in the parking lot.*
```text
3 4
Enter motorcycle SGX1234A 1613541902
Enter car SGF9283P 1613541902
Exit SGX1234A 1613545602
Enter car SGP2937F 1613546029
Enter car SDW2111W 1613549730
Enter car SSD9281L 1613549740
Exit SDW2111W 1613559745
```
### Output format
```text
Accept MotorcycleLot1
Accept CarLot1
MotorcycleLot1 2
Accept CarLot2
Accept CarLot3
Reject
CarLot3 6
```