import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Acts as the simulation's central controller.
 * Initializes the simulation parameters, including simulation time and the number of check-in counters for business
 * and coach classes.
 *
 * Within a loop that represents each minute of simulation time, it generates passengers based on certain probabilities,
 * indicating whether they are commuter or provincial, their class (business or coach), and the number of bags they carry.
 * Each passenger is then processed through the check-in counters, security screening, and gate procedure.
 * Finally, it calculates the total revenue, costs, and profits from the operation.
 *
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 */
public class Main {
    static List<Passenger> processedPassengers = new ArrayList<>();

    /**
     * Calculates the total revenue generated from the operation based on ticket prices.
     *
     * @param passengers The list of processed passengers
     * @return The total revenue
     */
    private static double calculateTotalRevenue(List<Passenger> passengers) {
        double revenue = 0;
        for (Passenger passenger : passengers) {
            if (passenger.isCommuter()) {
                revenue += 200; // Commuter ticket price
            } else {
                revenue += passenger.isBusinessClass() ? 1000 : 500; // Business or coach provincial ticket price
            }
        }
        return revenue;
    }

    /**
     * Calculates the total costs incurred during the operation, including flight operation and check-in agent costs.
     *
     * @param passengers         The list of processed passengers
     * @param simulationTimeHours The total simulation time in hours
     * @return The total costs
     */
    private static double calculateTotalCosts(List<Passenger> passengers, int simulationTimeHours) {
        // Assuming an equal distribution of provincial and commuter flights
        int numberOfProvincialFlights = simulationTimeHours / 6; // Every 6 hours
        int numberOfCommuterFlights = simulationTimeHours; // Hourly flights

        double flightOperationCosts = numberOfProvincialFlights * 12000 + numberOfCommuterFlights * 1500;
        double checkInAgentCosts = simulationTimeHours * 35; // $35 per hour

        return flightOperationCosts + checkInAgentCosts;
    }

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------------------------\n");
        System.out.println("----------------------------------------------------------------------------------------\n");
        System.out.printf("--------------Welcome to the Smiths Falls/Montague Airport Simulator--------------------\n");
        System.out.println("----------------------------------------------------------------------------------------\n");
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println();

        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for simulation time and flight departure time
        System.out.println("Enter simulation time in minutes (e.g. 720 for 12 hours, 1440 for 24 hours, 4320 for 3 days...): ");
        int SIMULATION_TIME = scanner.nextInt(); // Read simulation time from user

        System.out.println("Enter the number of business class counters (minimum 1): ");
        int numBusinessCounters = Math.max(scanner.nextInt(), 1); // Ensuring at least 1

        System.out.println("Enter the number of coach class counters (minimum 3, maximum 5): ");
        int numCoachCounters = Math.min(Math.max(scanner.nextInt(), 3), 5); // Ensuring at least 3 and at most 5


        // Close the scanner
        scanner.close();
        System.out.println();

        // Initialize the simulation components with user inputs where necessary
        PassengerArrival arrival = new PassengerArrival();
        CheckInCounter checkIn = new CheckInCounter(numBusinessCounters, numCoachCounters);
        SecurityScreening screening = new SecurityScreening();
        GateProcedure gate = new GateProcedure(0.1);


        for (int currentTime = 0; currentTime <= SIMULATION_TIME; currentTime++) {

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

        double totalRevenue = calculateTotalRevenue(processedPassengers);
        double totalCosts = calculateTotalCosts(processedPassengers, SIMULATION_TIME);
        double profits = totalRevenue - totalCosts;

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------\n");
        System.out.println("----------------------------------------------------------------------------------------\n");


        System.out.println("Profits: $" + String.format("%.2f",profits));

        System.out.println("Average Check-In Time: " + String.format("%.2f",checkIn.getAverageCheckInTime()) + " minutes");
        System.out.println("Average Screening Time: " + String.format("%.2f",screening.getAverageScreeningTime()) + " minutes");
        System.out.println("Average Waiting Time: " + String.format("%.2f",gate.getAverageWaitingTime()) + " minutes");


        System.out.println("----------------------------------------------------------------------------------------\n");
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.printf("------------Thank you for using the Smiths Falls/Montague Airport Simulator-------------\n");
        System.out.println("----------------------------------------------------------------------------------------\n");
        System.out.println("----------------------------------------------------------------------------------------\n");

    }
}
