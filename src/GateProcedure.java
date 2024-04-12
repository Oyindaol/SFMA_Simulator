import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages the final stage before boarding, processing passengers based on their flight departure time, which is set
 * according to their type (commuter or provincial) and the simulation time.
 * Implements logic to ensure passengers make their flight based on their arrival time at the gate or wait for the next
 * available flight.
 *
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 */
public class GateProcedure {
    private Queue<Passenger> commuterQueue;
    private Queue<Passenger> provincialQueue;
    private double flightDepartureTime; // Simulated departure time
    private double totalWaitingTime = 0;
    private int waitingPassengerCount = 0;


    /**
     * GateProcedure class constructor.
     * Initializes the queues for both business class and coach.
     *
     * @param flightDepartureTime The simulated departure time of the flight
     */
    public GateProcedure(double flightDepartureTime) {
        commuterQueue = new LinkedList<>();
        provincialQueue = new LinkedList<>();
        this.flightDepartureTime = 0.1;
    }

    /**
     * Calculates the average waiting time per passenger.
     *
     * @return The average waiting time per passenger
     */
    public double getAverageWaitingTime() {
        return waitingPassengerCount > 0 ? totalWaitingTime / waitingPassengerCount : 0;
    }

    /**
     * Simulates gate procedures for provincial passengers.
     * Checks if passenger arrived early enough and displays relevant messages.
     *
     * @param passenger The passenger object
     */
    private void processProvincialPassenger(Passenger passenger) {
        double arrivalTime = passenger.getArrivalTime();
        double minimumArrivalTime = flightDepartureTime - 90; // 90 minutes before departure

        if (arrivalTime >= minimumArrivalTime) {
            System.out.println("  - Gate Update: Made the flight.");
        } else {
            System.out.println("  - Gate Update: Missed the flight.");
            // Assuming automatic refund for early arrivals with missed flights
            if (arrivalTime >= flightDepartureTime - 210) { // 210 minutes before departure (3.5 hours)
                System.out.println("  - Full refund issued due to airport congestion.");
            }
        }
    }

    /**
     * Simulates gate procedures for commuter passengers.
     * Passengers wait in the queue until a simulated "next flight" arrives.
     *
     * @param passenger The passenger object
     */
    private void processCommuterPassenger(Passenger passenger) {
        System.out.println("  - Gate Update: Waiting for next flight.");
    }

    /**
     * Processes the passenger by dividing the queues to available counters.
     *
     * @param passenger The passenger object
     */
    public void processPassenger(Passenger passenger) {

        // Assuming method to get the next flight departure time based on passenger type and current simulation time
        double nextFlightTime = getNextFlightTime(passenger.isCommuter(), passenger.getArrivalTime());
        passenger.setFlightDepartureTime(nextFlightTime);

        // Calculate waiting time as the difference between flight departure time and passenger arrival time
        double waitingTime = nextFlightTime - passenger.getArrivalTime();
        System.out.println("  - Waiting time: " + String.format("%.2f",waitingTime) + " minutes.");

        if (passenger.isCommuter()) {
            commuterQueue.offer(passenger);
            processCommuterPassenger(passenger);
        } else {
            provincialQueue.offer(passenger);
            processProvincialPassenger(passenger);
        }

        // Update the total waiting time and count of processed passengers
        totalWaitingTime += waitingTime;
        waitingPassengerCount++;
    }

    /**
     * Determines the next flight departure time based on the passenger's type and the current simulation time.
     *
     * @param isCommuter  True if the passenger is a commuter, False otherwise
     * @param currentTime The current simulation time in minutes
     * @return The next flight departure time in minutes
     */
    double getNextFlightTime(boolean isCommuter, double currentTime) {
        double nextFlightTime;

        if (isCommuter) {
            // For commuter flights departing every hour on the half-hour
            // Find the next half-hour mark after the current time
            int hours = (int)currentTime / 60;
            int minutes = (int)currentTime % 60;
            if (minutes < 30) {
                // The next flight is later in the same hour
                nextFlightTime = hours * 60 + 30;
            } else {
                // The next flight is at the half-hour mark of the next hour
                nextFlightTime = (hours + 1) * 60 + 30;
            }
        } else {
            // For provincial flights departing every six hours (midnight, 6 am, noon, 6 pm)
            // Calculate the number of six-hour blocks that have passed since midnight
            int sixHourBlocks = (int)currentTime / (6 * 60);
            nextFlightTime = (sixHourBlocks + 1) * (6 * 60);

        }

        return nextFlightTime;
    }

}
