import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 *
 * Handles the security screening of passengers, again dividing them into business class and coach based on the number
 * of screening machines available for each.
 * Simulates the screening process by generating a random service time for each passenger.
 */
public class SecurityScreening {

    private Queue<Passenger> businessClassQueue;
    private Queue<Passenger> coachQueue;
    private Random random;
    private final int numBusinessMachines = 1;
    private final int numCoachMachines = 2;
    private final double averageScreeningTime = 3.0; // Minutes
    private double totalScreeningTime = 0;
    private int screeningPassengerCount = 0;


    /**
     * SecurityScreening class constructor.
     * Initializes the queues for both business class and coach.
     */
    public SecurityScreening() {
        businessClassQueue = new LinkedList<>();
        coachQueue = new LinkedList<>();
        random = new Random();
    }

    public Queue<Passenger> getBusinessClassQueue() {
        return businessClassQueue;
    }

    public Queue<Passenger> getCoachQueue() {
        return coachQueue;
    }

    public double getAverageScreeningTime() {
        return screeningPassengerCount > 0 ? totalScreeningTime / screeningPassengerCount : 0;
    }

    /**
     * Generates a random service time based on exponential distribution with the given average.
     *
     * @return Random service time in minutes
     */
    public double generateScreeningTime() {
        return generateExponentialServiceTime(averageScreeningTime);
    }

    public double generateExponentialServiceTime(double averageServiceTime) {
        return -Math.log(random.nextDouble()) * averageServiceTime;
    }

    /**
     * Simulates security screening process for a single machine.
     * @param queue
     */
    public void simulateSecurityScreening(Queue<Passenger> queue) {
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

        double serviceTime = generateScreeningTime();

        // Update the total screening time and count of processed passengers
        totalScreeningTime += serviceTime;
        screeningPassengerCount++;
    }
}