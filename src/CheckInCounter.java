import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 1
 */
public class CheckInCounter {
    private Queue<Passenger> businessClassQueue;
    private Queue<Passenger> coachQueue;
    private Random random;

    /**
     * CheckInCounter class constructor.
     * Initializes the queues for both business class and coach.
     */
    public CheckInCounter() {
        businessClassQueue = new LinkedList<>();
        coachQueue = new LinkedList<>();
        random = new Random();
    }

    /**
     * Simulate the check-in process.
     * @param queue
     * @param numCounters
     */
    private void simulateCheckInOperations(Queue<Passenger> queue, int numCounters) {
        for (int i = 0; i < numCounters; i++) {
            if (!queue.isEmpty()) {
                Passenger currentPassenger = queue.poll();
                // check-in operations ...
            }
        }
    }

    /**
     * Processes the passenger by dividing the queues to available counters.
     * @param passenger
     */
    public void processPassenger(Passenger passenger) {
        if (passenger.isBusinessClass()) {
            businessClassQueue.offer(passenger);
        } else {
            coachQueue.offer(passenger);
        }

        // Simulate check-in counter operations
        simulateCheckInOperations(businessClassQueue, 1); // One counter for business class
        simulateCheckInOperations(coachQueue, 3); // Three counters for coach class

        System.out.println("Processing passenger at Check-In Counters");
    }

    /**
     * Display the results of the simulation.
     */
    public void displayResults() {
        System.out.println();
    }
}
