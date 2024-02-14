/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 1
 */
public class Main {
    private static final int SIMULATION_TIME = 720; // 12 hours in minutes

    public static void main(String[] args) {
        PassengerArrival arrival = new PassengerArrival();
        CheckInCounter checkIn = new CheckInCounter();
        SecurityScreening screening = new SecurityScreening();
        GateProcedure gate = new GateProcedure();

        for (int currentTime = 0; currentTime < SIMULATION_TIME; currentTime++) {
            // Passenger arrivals
            boolean isCommuter = arrival.isCommuter(currentTime);
            double arrivalTime = arrival.generateNextArrivalTime(isCommuter);
            Passenger passenger = new Passenger(currentTime, arrivalTime, isCommuter);

            // Check-in counters
            checkIn.processPassenger(passenger);

            // Security screening
            screening.processPassenger(passenger);

            // Gate procedures
            gate.processPassenger(passenger);
        }

        // Display final simulation results
        checkIn.displayResults();
        screening.displayResults();
        gate.displayResults();
    }
}
