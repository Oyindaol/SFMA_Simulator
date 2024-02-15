import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 1.0
 */
public class GateProcedure {
    private Queue<Passenger> commuterQueue;
    private Queue<Passenger> provincialQueue;

    /**
     * GateProcedure class constructor.
     * Initializes the queues for both business class and coach.
     */
    public GateProcedure() {
        commuterQueue = new LinkedList<>();
        provincialQueue = new LinkedList<>();
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
        } else {
            provincialQueue.offer(passenger);
        }

        // Simulate gate procedures
        simulateGateProcedures(commuterQueue);
        simulateGateProcedures(provincialQueue);

        System.out.println("Processing passenger at Gate Procedures");
    }

    /**
     * Display the results of the simulation.
     */
    public void displayResults() {
        System.out.println();
    }
}
