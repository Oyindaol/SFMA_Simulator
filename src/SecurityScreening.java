import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 1.0
 */
public class SecurityScreening {
    private Queue<Passenger> businessClassQueue;
    private Queue<Passenger> coachQueue;
    private Random random;

    /**
     * SecurityScreening class constructor.
     * Initializes the queues for both business class and coach.
     */
    public SecurityScreening() {
        businessClassQueue = new LinkedList<>();
        coachQueue = new LinkedList<>();
        random = new Random();
    }

    /**
     * Simulate security screening process.
     * @param queue
     * @param numMachines
     */
    private void simulateSecurityScreening(Queue<Passenger> queue, int numMachines) {
        for (int i = 0; i < numMachines; i++) {
            if (!queue.isEmpty()) {
                Passenger currentPassenger = queue.poll();
                // security screening operations ...
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

        // Simulate security screening operations
        simulateSecurityScreening(businessClassQueue, 1); // One machine for business class
        simulateSecurityScreening(coachQueue, 2); // Two machines for coach class

        System.out.println("Processing passenger at Security Screening");
    }

    /**
     * Display the results of the simulation.
     */
    public void displayResults() {
        System.out.println();
    }
}
