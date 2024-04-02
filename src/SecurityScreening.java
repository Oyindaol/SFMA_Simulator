import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 */
public class SecurityScreening {
    private Queue<Passenger> businessClassQueue;
    private Queue<Passenger> coachQueue;
    private Random random;
    private final int numBusinessMachines = 1;
    private final int numCoachMachines = 2;
    private final double averageScreeningTime = 3.0; // Minutes

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
     * Generates a random service time based on exponential distribution with the given average.
     *
     * @return Random service time in minutes
     */
    private double generateScreeningTime() {
        return generateExponentialServiceTime(averageScreeningTime);
    }

    private double generateExponentialServiceTime(double averageServiceTime) {
        return -Math.log(random.nextDouble()) * averageServiceTime;
    }

    /**
     * Simulates security screening process for a single machine.
     * @param queue
     */
    private void simulateSecurityScreening(Queue<Passenger> queue) {
        if (!queue.isEmpty()) {
            Passenger currentPassenger = queue.poll();
            double serviceTime = generateScreeningTime();
            System.out.println("  - Screening Time: " + String.format("%.2f", serviceTime) + " minutes");
        }
    }

    /**
     * Processes the passenger by dividing the queues to available machines.
     * @param passenger
     */
    public void processPassenger(Passenger passenger) {
        Queue<Passenger> queue;
        int numMachines;

        if (passenger.isBusinessClass()) {
            queue = businessClassQueue;
            numMachines = numBusinessMachines;
        } else {
            queue = coachQueue;
            numMachines = numCoachMachines;
        }

        queue.offer(passenger);

        // Simulate security screening operations
        for (int i = 0; i < numMachines; i++) {
            simulateSecurityScreening(queue);
        }
    }
}