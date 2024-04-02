import java.util.Scanner;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 */
public class Main {

    public static void main(String[] args) {

        System.out.printf("Welcome to the Smiths Falls/Montague Airport Simulator.\n");
        System.out.println();

        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for simulation time and flight departure time
        System.out.println("Enter simulation time in minutes (e.g., 720 for 12 hours): ");
        int SIMULATION_TIME = scanner.nextInt(); // Read simulation time from user

        System.out.println("Enter flight departure time in minutes from simulation start (e.g., 480 for 8 hours into simulation): ");
        double flightDepartureTime = scanner.nextDouble(); // Read flight departure time from user

        // Close the scanner
        scanner.close();
        System.out.println();

        // Initialize the simulation components with user inputs where necessary
        PassengerArrival arrival = new PassengerArrival();
        CheckInCounter checkIn = new CheckInCounter();
        SecurityScreening screening = new SecurityScreening();
        GateProcedure gate = new GateProcedure(flightDepartureTime);


        for (int currentTime = 0; currentTime <= SIMULATION_TIME; currentTime++) {
            // Randomly decide if the passenger is a commuter or not
//            boolean isCommuter = arrival.isCommuter(currentTime); // Use the updated method
//            double arrivalTime = arrival.generateNextArrivalTime(isCommuter);
//            int numBags = arrival.generateNumberOfBags(isCommuter);
//            boolean isBusinessClass = isCommuter ? arrival.isCommuterBusinessClass() : arrival.isProvincialBusinessClass();
//
//            Passenger passenger = new Passenger(currentTime, arrivalTime, isCommuter, isBusinessClass, numBags);

            boolean isCommuter = arrival.isCommuter(currentTime);
            double arrivalTime = arrival.generateNextArrivalTime(isCommuter);
            int numBags = arrival.generateNumberOfBags(isCommuter);
            boolean isBusinessClass = false; // Default to false

            if (!isCommuter) { // Only non-commuters can be in business class
                isBusinessClass = arrival.isProvincialBusinessClass();
            }

            Passenger passenger = new Passenger(currentTime, arrivalTime, isCommuter, isBusinessClass, numBags);

            // Process the passenger through the simulation components
            checkIn.processPassenger(passenger);
            screening.processPassenger(passenger);
            gate.processPassenger(passenger);
            System.out.println();
        }

    }
}
