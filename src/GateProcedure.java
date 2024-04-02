import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 */
public class GateProcedure {
    private Queue<Passenger> commuterQueue;
    private Queue<Passenger> provincialQueue;
    private double flightDepartureTime; // Simulated departure time


    /**
     * GateProcedure class constructor.
     * Initializes the queues for both business class and coach.
     *
     * @param flightDepartureTime The simulated departure time of the flight
     */
    public GateProcedure(double flightDepartureTime) {
        commuterQueue = new LinkedList<>();
        provincialQueue = new LinkedList<>();
        this.flightDepartureTime = flightDepartureTime;
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
                System.out.println("      - Full refund issued due to airport congestion.");
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
     * Simulate gate procedure process.
     * @param queue
     */
    private void simulateGateProcedures(Queue<Passenger> queue) {
        while (!queue.isEmpty()) {
            Passenger currentPassenger = queue.poll();
            // gate procedures ...
        }
    }

    /**
     * Processes the passenger by dividing the queues to available counters.
     * @param passenger
     */
    public void processPassenger(Passenger passenger) {
        if (passenger.isCommuter()) {
            commuterQueue.offer(passenger);
            processCommuterPassenger(passenger);
        } else {
            provincialQueue.offer(passenger);
            processProvincialPassenger(passenger);
        }
    }

}
