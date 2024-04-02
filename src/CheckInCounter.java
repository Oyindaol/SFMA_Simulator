import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 */
public class CheckInCounter {
    private Queue<Passenger> businessClassQueue;
    private Queue<Passenger> coachQueue;
    private Random random;
    private final int numBusinessCounters = 1;
    private final int numCoachCounters = 3;
    private final double serviceTimePrintBoardingPass = 2.0; // Minutes
    private final double serviceTimeCheckBag = 1.0; // Minutes per bag


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
     * Simulates service time for a single activity (boarding pass, bag check, delays).
     * @param avgServiceTime Average service time for the activity
     * @return Service time in minutes
     */
    private double generateServiceTime(double avgServiceTime) {
        return -Math.log(random.nextDouble()) * avgServiceTime;
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
        Queue<Passenger> queue;
        int availableCounters;

        if (passenger.isBusinessClass()) {
            queue = businessClassQueue;
            availableCounters = numBusinessCounters;
        } else {
            queue = coachQueue;
            availableCounters = numCoachCounters;
        }

        // Check for available counters and create overflow queues if necessary
        if (queue.size() >= availableCounters) {
            if (passenger.isBusinessClass()) {
                System.out.println("Business Class queue full, creating overflow queue.");
                System.out.println();
                queue = new LinkedList<>(); // Create temporary overflow queue for business class
            } else {
                System.out.println("Coach queue full, creating overflow queue.");
                System.out.println();
                queue = new LinkedList<>(); // Create temporary overflow queue for coach
            }
        }

        queue.offer(passenger);

        // Simulate service times
        double totalServiceTime = 0;
        totalServiceTime += generateServiceTime(serviceTimePrintBoardingPass);
        int numBags = passenger.getNumberOfBags();
        totalServiceTime += numBags * generateServiceTime(serviceTimeCheckBag);
        totalServiceTime += generateServiceTime(3.0); // Other problems and delays
        String classType = passenger.isBusinessClass() ? "Business" : "Coach";

        // Simulate waiting time (placeholder, needs integration with simulation loop)
        double waitingTime = 0; // Replace with actual waiting time calculation from simulation loop
        System.out.println("Passenger " + passenger.getId() + " (" + (passenger.isCommuter() ? "Commuter" : "Provincial") + "):");
        System.out.println("Passenger " + passenger.getId() + " (" + classType + "): Processing at Check-In");
        System.out.println("  - Number of Bags: " + numBags);
        System.out.println("  - Waiting Time: " + waitingTime + " minutes");
        System.out.println("  - Check in Time: " + String.format("%.2f", totalServiceTime) + " minutes");

    }

}
